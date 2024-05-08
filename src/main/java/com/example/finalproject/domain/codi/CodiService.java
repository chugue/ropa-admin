package com.example.finalproject.domain.codi;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.codiItems.CodiItems;
import com.example.finalproject.domain.codiItems.CodiItemsRepository;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.items.ItemsRepository;
import com.example.finalproject.domain.love.Love;
import com.example.finalproject.domain.love.LoveRepository;
import com.example.finalproject.domain.photo.Photo;
import com.example.finalproject.domain.photo.PhotoRepository;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CodiService {
    private final CodiItemsRepository codiItemsRepository;
    private final PhotoRepository photoRepository;
    private final LoveRepository loveRepository;
    private final ItemsRepository itemsRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final CodiRepository codiRepository;
    private final String uploadPath = "./upload/";

    //코디 등록 페이지 - 아이템 연결
    public List<CodiResponse.BrandInfo> addItemPage() {
        //모든 브랜드 정보 불러오기
        List<Admin> admins = adminRepository.findAdminByPhoto();

        //브랜드 기준으로 본인이 올린 아이템 정보 불러오기
        List<CodiResponse.BrandInfo> respList = admins.stream().map(admin -> {
            List<Items> adminItems = itemsRepository.findByAdminItemsAndPhotos(admin.getId());
            return new CodiResponse.BrandInfo(admin, adminItems);
        }).toList();
        return respList;
    }




    // 코디 보기 페이지 요청 - 페이지 내 아이템 목록, 크리에이터 코디목록 포함
    public CodiResponse.MainViewDTO codiPage(Integer codiId, Integer userId) {

        // codiId로 코디 메인 사진들 조회
        List<Photo> mainCodiPhotos = photoRepository.findByCodiId(codiId);

        // 해당 코디에 대한 사용자의 좋아요 상태 확인 + 해당 코디의 전체 좋아요 갯수
        Optional<Love> loveStatus = loveRepository.findByCodiIdAndUserLoveStatus(codiId, userId);
        Long totalLove = loveRepository.countTotalLove(codiId);

        // codiItems로 조회해서 Codi 정보랑 연계된 Items조회후 사진 가져오기
        List<CodiItems> codiItemsList = codiItemsRepository.findByCodiWithItems(codiId);
        List<Integer> itemsIdList = codiItemsList.stream().map(codiItems -> codiItems.getItems().getId()).toList();
        List<Photo> codiItemPhotos = photoRepository.findByItemsIds(itemsIdList);

        // CreatorId로 모든 코디를 조회해서 여러 코디 메인 사진 가져오기
        Codi selectedCodi = codiItemsList.getFirst().getCodi();
        List<Photo> otherCodiPhotos = photoRepository.findByUserIdWithCodiesAndPhoto(selectedCodi.getUser().getId());

        return new CodiResponse.MainViewDTO(
                selectedCodi, loveStatus, totalLove, mainCodiPhotos, codiItemPhotos, otherCodiPhotos);

    }

    // 앱에서 코디저장 요청과 아이템 연결
    @Transactional
    public CodiResponse.NewLinkItems saveCodiAndItems(CodiRequest.SaveDTO reqDTO) {
        // 요청된 데이터에서 items Id를 추출 후 영속객체 찾기
        List<Integer> reqItemsIds = reqDTO.getItems().stream().map(itemCodiDTO ->
                itemCodiDTO.getItemsId()).toList();
        List<Items> linkItems = itemsRepository.findItemsByItemId(reqItemsIds);

        // 데이터 연결을 위한 영속 user객체 찾기
        User user = userRepository.findById(reqDTO.getUserId())
                .orElseThrow(() -> new Exception404("사용자 정보를 찾을 수 없습니다."));

        // 새로운 코디 객체 생성
        Codi savedCodi = codiRepository.save(Codi.builder()
                .title(reqDTO.getTitle())
                .description(reqDTO.getDescription())
                .user(user).build());

        // 새로 등록된 코디 사진을 파일로 저장하고 DB에 영속화 그리고 코디랑 연결
        List<Photo> savedPhotos = new ArrayList<>();
        reqDTO.getCodiPhotos().forEach(appSaveDTO ->
               savedPhotos.add(uploadCodiImage(appSaveDTO, savedCodi)));

        // 새로 생성된 코디와 기존의 영속화된 아이템들을 연결
        List<CodiItems> linkedCodiItems = linkItems.stream().map(items -> new CodiItems().builder()
                .codi(savedCodi)
                .items(items).build()).toList();

        return new CodiResponse.NewLinkItems(savedCodi, savedPhotos, linkedCodiItems);

    }

    @Transactional
    public Photo uploadCodiImage(CodiRequest.SaveDTO.AppSaveDTO image, Codi codi) {

        // 파일명 중복 방지를 위해서 UUID 적용
        String imgFilename = UUID.randomUUID() + "_" + image.getPhotoName();

        // resourceHandler로 해당 폴더 개방 작업을 WebConfig에서 등록하고 여기 와야됨
        // 파일이름이랑 개방된 폴더를 조합해서 경로생성
        Path imgPath = Paths.get(uploadPath + imgFilename);

        // 파일저장 핵심로직
        // 파일 저장 로직 매개변수로 경로와 사진의 바이트 정보를 요구함
        // 파일 저장 향후 파일 사이즈 유효성 추가 해야될것 TODO
        validationCheckAndSave(image, imgPath);

        // DB저장 전 DB전용으로 경로 수정
        String dbPath = "/upload/" + imgFilename;
        if (image.getIsMainPhoto()) {
            return photoRepository.save(Photo.builder()
                    .codi(codi)
                    .path(dbPath)
                    .name(image.getPhotoName())
                    .sort(Photo.Sort.CODI)
                    .isMainPhoto(true)  // 대표사진이라면 꼭 true 남겨주기
                    .createdAt(Timestamp.from(Instant.now())).build());
        } else {
            return photoRepository.save(Photo.builder()
                    .codi(codi)
                    .path(dbPath)
                    .name(image.getPhotoName())
                    .sort(Photo.Sort.CODI)
                    .isMainPhoto(false)
                    .createdAt(Timestamp.from(Instant.now())).build());
        }
    }


    // 파일로 저장 + 예외처리
    @Transactional
    protected void validationCheckAndSave(CodiRequest.SaveDTO.AppSaveDTO image, Path imgPath) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(image.getPhotoBase64());
            Files.write(imgPath, decodedBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


