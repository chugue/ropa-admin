package com.example.finalproject.domain.user;


import com.example.finalproject._core.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;

    // 앱] 로그인 요청
    @PostMapping("/app/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        UserResponse.LoginDTO respDTO = userService.login(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 앱] 회원가입
    @PostMapping("/app/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        UserResponse.JoinDTO respDTO = userService.join(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 앱 세팅 화면
    @GetMapping("/app/setting/{userId}")
    public ResponseEntity<?> settingPage(@PathVariable Integer userId) {
        UserResponse.SettingPageDTO respDTO = userService.settingPage(userId);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 앱 프로필 화면
    @GetMapping("/app/profile/{userId}")
    public ResponseEntity<?> profilePage(@PathVariable Integer userId) {
        UserResponse.ProfilePageDTO respDTO = userService.profilePage(userId);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
