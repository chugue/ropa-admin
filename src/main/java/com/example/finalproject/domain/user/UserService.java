package com.example.finalproject.domain.user;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject.domain.codiItems.CodiItems;
import com.example.finalproject.domain.codiItems.CodiItemsRepository;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.items.ItemsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CodiItemsRepository codiItemsRepository;
    private final ItemsRepository itemsRepository;

    //회원가입
    @Transactional
    public User join(UserRequest.JoinDTO reqDTO) {
        User user = userRepository.save(User.builder()
                .email(reqDTO.getEmail())
                .password(reqDTO.getPassword())
                .nickName(reqDTO.getNickName())
                .status("신청전")
                .createdAt(reqDTO.getCreatedAt())
                .blueChecked(false)
                .build());
        return user;
    }

    // 앱 사용자 로그인
    public User login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return user;
    }

    // 앱 세팅 페이지
    public UserResponse.SettingPageDTO settingPage(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.SettingPageDTO(user);
    }

    // 앱 사용자 프로필 페이지
    public UserResponse.ProfilePageDTO profilePage(SessionUser sessionUser) {
        User user = userRepository.findByUserIdWithPhoto(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.ProfilePageDTO(user, new UserResponse.ProfilePageDTO.PhotoDTO(user.getPhoto()));
    }

    // 앱 사용자 크리에이터 지원 페이지
    public UserResponse.CreatorApplyDTO creatorApplyPage(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.CreatorApplyDTO(user);
    }

    // 앱 사용자 크리에이터 지원
    @Transactional
    public UserResponse.CreatorApplyDTO creatorApply(UserRequest.CreatorApplyDTO creatorApplyDTO, SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));

        user.setHeight(creatorApplyDTO.getHeight());
        user.setWeight(creatorApplyDTO.getWeight());
        user.setInstagram(creatorApplyDTO.getInstagram());
        user.setJob(creatorApplyDTO.getJob());
        user.setStatus("승인 대기");

        userRepository.save(user);

        return new UserResponse.CreatorApplyDTO(user);
    }

    //크리에이터 뷰 페이지
    public UserResponse.CreatorViewDTO creatorView(SessionUser sessionUser, Integer id) {
        // 1. 세션에서 사용자 정보 가져오기
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));

        // 2. 선택된 크리에이터의 정보와 관련된 코디 아이템을 가져오기
        List<CodiItems> codiItemsList = codiItemsRepository.findCodiItemsByUserId(id);

        // 3. 코디 아이템을 기반으로 아이템 리스트 가져오기
        List<Items> itemsList = itemsRepository.findItemsByCodiItems(codiItemsList);

        // 4. DTO로 매핑하기
        UserResponse.CreatorViewDTO respDTO = new UserResponse.CreatorViewDTO(user, codiItemsList, itemsList);

        return respDTO;
    }


}
