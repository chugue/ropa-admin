package com.example.finalproject.domain.user;

import com.example.finalproject._core.error.exception.Exception401;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    public User join() {

        return new User();
    }

    // 앱 사용자 로그인
    public UserResponse.LoginDTO login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.LoginDTO(user);
    }
}
