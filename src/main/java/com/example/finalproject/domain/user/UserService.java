package com.example.finalproject.domain.user;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.codi.CodiRepository;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.items.ItemsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CodiRepository codiRepository;
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
    public UserResponse.CreatorApply creatorApply(UserRequest.CreatorApplyDTO creatorApplyDTO, SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));

        user.setHeight(creatorApplyDTO.getHeight());
        user.setWeight(creatorApplyDTO.getWeight());
        user.setInstagram(creatorApplyDTO.getInstagram());
        user.setJob(creatorApplyDTO.getJob());
        user.setStatus("승인 대기");

        userRepository.save(user);

        return new UserResponse.CreatorApply(user);
    }

    //크리에이터 뷰 페이지
    public UserResponse.CreatorViewDTO creatorView(SessionUser sessionUser, int userId) {
        // 1. 세션에서 사용자 정보 가져오기
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));

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

        UserResponse.UserInfo userDTO = new UserResponse.UserInfo(user);

        return new UserResponse.CreatorViewDTO(userDTO, codiDTOs, itemDTOs);
    }

}





