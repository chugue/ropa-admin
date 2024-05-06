package com.example.finalproject.domain.photo;

import com.example.finalproject._core.error.exception.Exception400;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.love.LoveRepository;
import com.example.finalproject.domain.love.LoveResponse;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
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
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final LoveRepository loveRepository;
    private final String uploadPath = "./upload/";


    // 아이템 메인 사진 업로드
    @Transactional
    public Photo uploadItemMainImage(MultipartFile mainImage, Items items) {
        if (mainImage == null || mainImage.isEmpty()) {
            throw new Exception400( "잘못된 요청입니다.");
        }
        // 파일명 중복 방지를 위해서 UUID 적용
        String imgFilename = UUID.randomUUID() + "_" + mainImage.getOriginalFilename();

        // resourceHandler로 해당 폴더 개방 작업을 WebConfig에서 등록하고 여기 와야됨
        // 파일이름이랑 개방된 폴더를 조합해서 경로생성
        Path imgPath = Paths.get(uploadPath + imgFilename);


        // 파일저장 핵심로직
        // 파일 저장 로직 매개변수로 경로와 사진의 바이트 정보를 요구함
        // 파일 저장 향후 파일 사이즈 유효성 추가 해야될것 TODO
        validationCheckAndSave(mainImage, imgPath);

        // DB저장 전 DB전용으로 경로 수정
        String dbPath = "/upload/" + imgFilename;


        // Base64는 디코딩해서 던져주고, MultiPartForm은 getBytes로 꺼냄
        Photo photo = photoRepository.save(Photo.builder()
                .items(items)
                .path(dbPath)
                .name(mainImage.getName())
                .sort(Photo.Sort.ITEM)
                .isMainPhoto(true)  // 대표사진이라면 꼭 true 남겨주기
                .createdAt(Timestamp.from(Instant.now())).build());
        return photo;
    }


    // 앱] 메인 홈 화면 요청
    public PhotoResponse.HomeDTO getHomeLists() {
        // 코디의 좋아요의 합으로 인기크리에이터를 좋아요받은 순으로 나열 + 대표 사진까지 찾기
        List<LoveResponse.UserLoveCount> userLoveCounts = loveRepository.findUserIdsSortedByLoveCount();
        List<Integer> popularCreators = userLoveCounts.stream().map(userLoveCount -> userLoveCount.getUserId()).toList();
        List<Photo> popularUserPhotos = photoRepository.findByUserId(popularCreators);

        // 인기 아이템의 id 조회 (총 판매량 순으로 아이템을 나열) + 사진 가져오기
        List<Integer> itemsId = orderHistoryRepository.findItemsIdByTotalSales();
        List<Photo> popularItemsPhotos = photoRepository.findByItemsIds(itemsId);

        // 인기 코디 좋아요 순으로 정렬
        List<LoveResponse.CodiLoveCount> popularCodies = loveRepository.findCodiIdsSortedByLoveCount();
        List<Integer> popularCodiIdes = popularCodies.stream().map(codiLoveCount -> codiLoveCount.getCodiId()).toList();
        List<Photo> popularCodiPhotos = photoRepository.findByCodiIds(popularCodiIdes);

        return new PhotoResponse.HomeDTO(popularUserPhotos, popularItemsPhotos, popularCodiPhotos);
    }



    // 아이템 상세정보 사진 업로드
    @Transactional
    public Photo uploadItemDetailImage(MultipartFile detailImage, Items items) {
        if (detailImage == null || detailImage.isEmpty()) {
            throw new Exception400( "잘못된 요청입니다.");
        }
        // 파일명 중복 방지를 위해서 UUID 적용
        String imgFilename = UUID.randomUUID() + "_" + detailImage.getOriginalFilename();

        // resourceHandler로 해당 폴더 개방 작업을 WebConfig에서 등록하고 여기 와야됨
        // 파일이름이랑 개방된 폴더를 조합해서 경로생성
        Path imgPath = Paths.get(uploadPath + imgFilename);

        // 파일저장 핵심로직
        // 파일 저장 로직 매개변수로 경로와 사진의 바이트 정보를 요구함
        // 파일 저장 향후 파일 사이즈 유효성 추가 해야될것 TODO
        validationCheckAndSave(detailImage, imgPath);

        // DB 저장 전 경로 구분자 변경
        String dbPath = "/upload/" + imgFilename;

        Photo photo = photoRepository.save(Photo.builder()
                .items(items)
                .path(dbPath)
                .name(detailImage.getName())
                .sort(Photo.Sort.ITEM)
                .isMainPhoto(false)  // 대표사진이라면 꼭 true 남겨주기
                .createdAt(Timestamp.from(Instant.now())).build());
        return photo;
    }


    // 파일로 저장 + 예외처리
    private void validationCheckAndSave(MultipartFile image, Path imgPath) {
        try {
            Files.write(imgPath, image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
