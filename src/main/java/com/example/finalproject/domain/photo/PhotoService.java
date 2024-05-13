package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.love.LoveRepository;
import com.example.finalproject.domain.love.LoveResponse;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    // 회원가입 사진 업로드
    @Transactional
    public void uploadBrandImage(MultipartFile brandImage, Admin admin) {
        if (brandImage == null || brandImage.isEmpty()) {
            return;
        }

        // 파일명 중복 방지를 위해 UUID 사용
        String imgFilename = UUID.randomUUID() + "_" + brandImage.getOriginalFilename();
        // resourceHandler로 해당 폴더 개방 작업을 WebConfig에서 등록하고 여기 와야됨
        // 파일이름이랑 개방된 폴더를 조합해서 경로 생성
        Path imgPath = Paths.get(uploadPath + imgFilename);

        //파일 저장 (fileWrite)
        //파일 저장 로직 매개변수로 경로와 사진의 바이트 정보를 요구한다.
        validationCheckAndSave(brandImage, imgPath);

        //DB저장 전 DB전용으로 경로 수정
        String dbPath = "/upload" + imgFilename;

        // Base64는 디코딩해서 던져주고, MultiPartForm은 getBytes로 꺼냄
        Photo photo = photoRepository.save(Photo.builder()
                .admin(admin)
                .path(dbPath)
                .uuidName(imgFilename)
                .originalFileName(brandImage.getOriginalFilename())
                .sort(Photo.Sort.BRAND)
                .isMainPhoto(true)  // 대표사진이라면 꼭 true 남겨주기
                .createdAt(Timestamp.from(Instant.now())).build());
    }

    // 아이템 메인 사진 업로드
    @Transactional
    public void uploadItemMainImage(MultipartFile mainImage, Items items) {
        if (mainImage == null || mainImage.isEmpty()) {
            return;
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
                .uuidName(imgFilename)
                .originalFileName(mainImage.getOriginalFilename())
                .sort(Photo.Sort.ITEM)
                .isMainPhoto(true)  // 대표사진이라면 꼭 true 남겨주기
                .createdAt(Timestamp.from(Instant.now())).build());
    }

    // 아이템 메인사진 없데이트
    @Transactional
    public void updateMainImage(MultipartFile updateImage, Photo dbPhoto, Items items) throws IOException {
        if (!updateImage.getOriginalFilename().equals(dbPhoto.getOriginalFileName())) {
            uploadItemMainImage(updateImage, items);
            deleteItemImage(dbPhoto);
        }
    }

    // 아이템 상세보기 사진 업데이트
    @Transactional
    public void updateDetailImage(MultipartFile updateImage, Photo dbPhoto, Items items) throws IOException {
        if (!updateImage.getOriginalFilename().equals(dbPhoto.getOriginalFileName())) {
            uploadItemDetailImage(updateImage, items);
            deleteItemImage(dbPhoto);
        }
    }


    // 파일로 저장 + 예외처리
    @Transactional
    protected void validationCheckAndSave(MultipartFile image, Path imgPath) {
        try {
            Files.write(imgPath, image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // 사진 삭제
    @Transactional
    public void deleteItemImage(Photo dbPhoto) {
        // 데이터베이스에 저장된 상대 경로
        String dbPath = dbPhoto.getPath();  // 예: /upload/uuid-filename.jpg

        // 파일 이름 추출을 위한 마지막 '/' 위치 확인
        int lastSlashIndex = dbPath.lastIndexOf("/");
        if (lastSlashIndex == -1) {
            throw new IllegalStateException("올바른 파일 경로가 아닙니다: " + dbPath);
        }
        // 상대 경로를 절대 경로로 변환
        String baseDirectory = System.getProperty("user.dir");  // 애플리케이션이 실행되는 디렉토리의 절대 경로
        String fullPath = baseDirectory + File.separator + "upload" + File.separator + dbPath.substring(dbPath.lastIndexOf("/") + 1);

        Path pathToDelete = Paths.get(fullPath);

        // 파일 존재 여부 확인 후 삭제
        try {
            if (Files.exists(pathToDelete)) {
                Files.delete(pathToDelete);
            } else {
                throw new IllegalStateException("파일이 존재하지 않습니다: " + pathToDelete);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 삭제 중 오류 발생", e);
        }
    }

    // 아이템 번호로 연결된 사진 삭제
    @Transactional
    public void deleteByItemId(Integer itemId) {
        List<Photo> itemPhotos = photoRepository.findAllByItemsId(itemId);

        itemPhotos.forEach(photo -> {
            deleteItemImage(photo);
            photoRepository.delete(photo);
        });
    }

    // 앱] 메인 홈 화면 요청
    public PhotoResponse.Home getHomeLists() {
        // 코디의 좋아요의 합으로 인기크리에이터를 좋아요받은 순으로 나열 + 대표 사진까지 찾기
        List<LoveResponse.UserLoveCount> userLoveCounts = loveRepository.findUserIdsSortedByLoveCount();
        List<Integer> popularCreators = userLoveCounts.stream().map(userLoveCount -> userLoveCount.getUserId()).toList();
        List<Photo> popularUserPhotos = photoRepository.findByUserId(popularCreators);

        // 인기 아이템의 id 조회 (총 판매량 순으로 아이템을 나열) + 사진 가져오기
//        List<Integer> itemsId = orderHistoryRepository.findItemsIdByTotalSales();
//        List<Photo> popularItemsPhotos = photoRepository.findByItemsIds(itemsId);

        List<LoveResponse.CodiLoveCount> popularCodies = loveRepository.findCodiIdsSortedByLoveCount();
        List<Integer> popularCodiIdes = popularCodies.stream().map(codiLoveCount -> codiLoveCount.getCodiId()).toList();
        List<Photo> popularCodiPhotos = photoRepository.findByCodiIds(popularCodiIdes);

        // 시연영상용으로 최신순 아이템
        List<Photo> popularItemsPhotos = photoRepository.finAllOrderBy();

        return new PhotoResponse.Home(popularUserPhotos, popularItemsPhotos, popularCodiPhotos);
    }


    // 아이템 상세정보 사진 업로드
    @Transactional
    public void uploadItemDetailImage(MultipartFile detailImage, Items items) {
        if (detailImage == null || detailImage.isEmpty()) {
            return;
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

        photoRepository.save(Photo.builder()
                .items(items)
                .path(dbPath)
                .uuidName(imgFilename)
                .originalFileName(detailImage.getOriginalFilename())
                .sort(Photo.Sort.ITEM)
                .isMainPhoto(false)  // 대표사진이라면 꼭 true 남겨주기
                .createdAt(Timestamp.from(Instant.now())).build());
    }
}
