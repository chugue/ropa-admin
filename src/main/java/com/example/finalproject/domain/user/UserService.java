package com.example.finalproject.domain.user;

import com.example.finalproject._core.error.exception.Exception400;
import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.codi.CodiRepository;
import com.example.finalproject.domain.codi.CodiResponse;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.items.ItemsRepository;
import com.example.finalproject.domain.items.ItemsResponse;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import com.example.finalproject.domain.photo.Photo;
import com.example.finalproject.domain.photo.PhotoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CodiRepository codiRepository;
    private final ItemsRepository itemsRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final PhotoRepository photoRepository;
    private final String uploadPath = "./upload/";


    // 프로필 변경
    @Transactional
    public UserResponse.ProfileUpdate updateProfile(UserRequest.ProfileUpdateDTO reqDTO, Integer userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));

        User newUser = user.toUpdate(reqDTO);
        Photo photo = null;
        // 사진을 보냈을을 경우에만 로직 실행
        if (reqDTO.getPhoto() != null) {
            Optional<Photo> photoOp = photoRepository.findById(user.getPhoto().getId());

            // 사진이 있으면 업데이트 없으면 사진 새로저장
            if (photoOp.isPresent()) {
                photo = updateUserImage(photoOp.get(), reqDTO.getPhoto(), newUser);
            } else {
                photo = saveUserImage(reqDTO.getPhoto(), newUser);
            }
        }
        User updatedUser = userRepository.save(newUser);
        return new UserResponse.ProfileUpdate(updatedUser, photo);
    }

    // 사용자 프로필 사진 저장
    @Transactional
    public Photo saveUserImage(UserRequest.ProfileUpdateDTO.PhotoDTO photo, User updatedUser) {

        String userPath = "user/" + updatedUser.getId();

        // 파일명 중복 방지를 위해 UUID 사용
        String imgFilename = UUID.randomUUID() + "_" + photo.getName();

        // resourceHandler로 해당 폴더 개방 작업을 WebConfig에서 등록하고 여기 와야됨
        // 파일이름이랑 개방된 폴더를 조합해서 경로 생성
        Path imgPath = Paths.get(uploadPath, userPath, imgFilename);

        //파일 저장 (fileWrite)
        //파일 저장 로직 매개변수로 경로와 사진의 바이트 정보를 요구한다.
        validationCheckAndSave(photo.getBase64(), imgPath);

        //DB저장 전 DB전용으로 경로 수정
        String dbPath = "/upload/" + userPath + "/" + imgFilename;

        // Base64는 디코딩해서 던져주고, MultiPartForm은 getBytes로 꺼냄
        return photoRepository.save(Photo.builder()
                .user(updatedUser)
                .path(dbPath)
                .uuidName(imgFilename)
                .originalFileName(photo.getName())
                .sort(Photo.Sort.USER)
                .isMainPhoto(true)  // 대표사진이라면 꼭 true 남겨주기
                .createdAt(Timestamp.from(Instant.now())).build());
    }


    // 브랜드 회원 정보 업데이트
    @Transactional
    public Photo updateUserImage(Photo oldPhoto, UserRequest.ProfileUpdateDTO.PhotoDTO newPhoto, User user) {

        // userId로 폴더 만들어서 경로 저장
        String userPath = "user/" + user.getId();
        String newUuidName = UUID.randomUUID() + "_" + newPhoto.getName();
        Path newImgPath = Paths.get(uploadPath, userPath, newUuidName);

        if (!Files.exists(newImgPath)) { //파일이 없다면 저장
            validationCheckAndSave(newPhoto.getBase64(), newImgPath);
        } else {
            System.out.println("파일이 이미 존재합니다: " + newImgPath);
        }
        // 경로 업데이트
        return updateUserPhoto(oldPhoto, newUuidName, userPath, newPhoto.getName());

    }

    @Transactional
    public Photo updateUserPhoto(Photo oldPhoto, String newUuidName, String userPath, String originalFileName) {
        String dbPath = "/upload/" + userPath + "/" + newUuidName;

        oldPhoto.setPath(dbPath);
        oldPhoto.setUuidName(newUuidName);
        oldPhoto.setOriginalFileName(originalFileName);
        oldPhoto.setUpdateAt(Timestamp.from(Instant.now())); // update 시간을 기록하려면 이 필드가 필요함

        return photoRepository.save(oldPhoto);
    }

    //회원가입
    @Transactional
    public User join(UserRequest.JoinDTO reqDTO) {
        Optional<User> userOp = userRepository.findByEmail(reqDTO.getEmail());

        if (userOp.isPresent()) {
            throw new Exception400("중복된 이메일이 있습니다.");
        }

        User user = userRepository.save(User.builder()
                .email(reqDTO.getEmail())
                .password(reqDTO.getPassword())
                .nickName(reqDTO.getNickName())
                .status("신청전")
                .blueChecked(false)
                .build());
        return user;
    }

    // 앱 사용자 로그인
    public User login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("사용자 정보를 찾을 수 없습니다."));
        return user;
    }

    // 앱 세팅 페이지
    public UserResponse.SettingPage settingPage(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.SettingPage(user);
    }

    // 앱 사용자 프로필 페이지
    public UserResponse.ProfilePage profilePage(SessionUser sessionUser) {
        User user = userRepository.findByUserIdWithPhoto(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.ProfilePage(user, new UserResponse.ProfilePage.PhotoInfo(user.getPhoto()));
    }

    // 앱 사용자 크리에이터 지원 페이지
    public UserResponse.CreatorApply creatorApplyPage(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.CreatorApply(user);
    }

    // 앱 사용자 크리에이터 지원
    @Transactional
    public UserResponse.CreatorApply creatorApply(UserRequest.CreatorApplyDTO reqDTO, SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));

        user.setHeight(reqDTO.getHeight());
        user.setWeight(reqDTO.getWeight());
        user.setInstagram(reqDTO.getInstagram());
        user.setIntroMsg(reqDTO.getComment());
        user.setJob(reqDTO.getJob());
        user.setBlueChecked(false);
        user.setStatus("승인 대기");
        user.setApplyTime(LocalDateTime.now());

        userRepository.save(user);

        return new UserResponse.CreatorApply(user);
    }

    //크리에이터 뷰 페이지
    public UserResponse.CreatorViewDTO creatorView(Integer userId) {
        // 1. 크리에이터 정보 불러오기
        User user = userRepository.findUsersByBlueCheckedAndPhoto(userId)
                .orElseThrow(() -> new Exception401("크리에이터가 아닙니다."));

        // 2. 선택된 크리에이터의 정보와 관련된 코디 목록 가져오기
        List<Codi> codis = codiRepository.findCodiByUserId(userId);

        // 3. 코디에 연결된 아이템 및 포토 정보 가져오기
        List<Items> itemsList = itemsRepository.findItemsByCodiIds(
                codis.stream().map(Codi::getId).collect(Collectors.toList()));

        // 4. DTO로 매핑하기
        List<UserResponse.CodiList> codiDTOs = codis.stream()
                .map(UserResponse.CodiList::new)
                .collect(Collectors.toList());

        List<UserResponse.ItemList> itemDTOs = itemsList.stream()
                .map(UserResponse.ItemList::new)
                .distinct()
                .collect(Collectors.toList());

        UserResponse.CreatorInfo userDTO = new UserResponse.CreatorInfo(user);

        return new UserResponse.CreatorViewDTO(userDTO, codiDTOs, itemDTOs);
    }

    //유저 마이페이지
    public UserResponse.UserMyPage userMyPage(SessionUser sessionUser) {
        // 1. 유저 정보 불러오기
        User user = userRepository.findByUserIdWithPhoto(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증 되지 않았습니다."));

        // 2. 주문 총 량 찾아오기
        Integer sumOrderItemQty = orderHistoryRepository.getTotalOrderItemQtyByUserId(Long.valueOf(sessionUser.getId()));

        // 3. UserResponse.UserMyPage 객체 생성 및 반환
        return new UserResponse.UserMyPage(user, sumOrderItemQty);
    }

    //크리에이터 마이페이지
    public UserResponse.CreatorMyPage creatorMyPage(SessionUser sessionUser) {
        // 1. 유저 정보 불러오기
        User user = userRepository.findUsersByBlueCheckedAndPhoto(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증 되지 않았습니다."));

        // 2. 주문 총 량 찾아오기
        Integer sumOrderItemQty = orderHistoryRepository.getTotalOrderItemQtyByUserId(Long.valueOf(sessionUser.getId()));

        // 2. 선택된 크리에이터의 정보와 관련된 코디 목록 가져오기
        List<Codi> codis = codiRepository.findCodiByUserId(sessionUser.getId());

        // 3. 코디에 연결된 아이템 및 포토 정보 가져오기
        List<Items> itemsList = itemsRepository.findItemsByCodiIds(
                codis.stream().map(Codi::getId).collect(Collectors.toList()));

        // 4. DTO로 매핑하기
        List<UserResponse.MyCodiList> codiDTOs = codis.stream()
                .map(UserResponse.MyCodiList::new)
                .collect(Collectors.toList());

        List<UserResponse.ItemList> itemDTOs = itemsList.stream()
                .map(UserResponse.ItemList::new)
                .distinct()
                .collect(Collectors.toList());

        UserResponse.CreatorMyInfo creatorInfoDTO = new UserResponse.CreatorMyInfo(user, sumOrderItemQty);

        // 3. UserResponse.UserMyPage 객체 생성 및 반환
        return new UserResponse.CreatorMyPage(creatorInfoDTO, codiDTOs, itemDTOs);
    }

    // 유저 아이템, 코디 통합 검색
    public UserResponse.SearchPage searchPage(String keyword) {
        List<Codi> codiList;
        List<Items> items;
        List<CodiResponse.CodiListDTO> codiListDTO;
        List<ItemsResponse.ItemListDTO> itemListDTO;

        if (keyword == null || keyword.isEmpty()) {
            codiList = codiRepository.findByAllCodi();
            items = itemsRepository.findByAllItems();
            codiListDTO = codiList.stream()
                    .map(CodiResponse.CodiListDTO::new).toList();
            itemListDTO = items.stream()
                    .map(ItemsResponse.ItemListDTO::new).toList();

            return new UserResponse.SearchPage(codiListDTO, itemListDTO);
        }

        items = itemsRepository.findItemsByItemName(keyword);
        codiList = codiRepository.findItemsByCodiTitle(keyword);

        codiListDTO = codiList.stream()
                .map(CodiResponse.CodiListDTO::new).toList();
        itemListDTO = items.stream()
                .map(ItemsResponse.ItemListDTO::new).toList();

        return new UserResponse.SearchPage(codiListDTO, itemListDTO);
    }


    // 파일로 저장 + 예외처리
    @org.springframework.transaction.annotation.Transactional
    protected void validationCheckAndSave(String base64, Path imgPath) {
        try {
            byte[] photoBytes = Base64.decodeBase64(base64);
            Files.createDirectories(imgPath.getParent());
            Files.write(imgPath, photoBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}





