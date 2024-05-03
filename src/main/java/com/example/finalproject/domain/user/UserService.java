package com.example.finalproject.domain.user;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.utils.AppJwtUtill;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public UserResponse.JoinDTO join(UserRequest.JoinDTO reqDTO) {
        User user = userRepository.save(User.builder()
                .email(reqDTO.getEmail())
                .password(reqDTO.getPassword())
                .nickName(reqDTO.getNickName())
                .status("신청전")
                .createdAt(reqDTO.getCreatedAt())
                .blueChecked(false)
                .build());
        return new UserResponse.JoinDTO(user);
    }

    //JWT 돌려주기
    public String login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));

        String jwt = AppJwtUtill.create(user);
        AppJwtUtill.verify(jwt);

        return jwt;
    }

    // 앱 사용자 로그인
    public UserResponse.LoginDTO loginByDTO(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.LoginDTO(user);
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
    public UserResponse.CreatorApplyDTO creatorApply(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.CreatorApplyDTO(user);
    }
}
