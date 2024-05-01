package com.example.finalproject.domain.user;


import com.example.finalproject._core.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;

    // 앱용 로그인 요청
    @PostMapping("/app/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        UserResponse.LoginDTO respDTO = userService.login(reqDTO);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "admin/join-form";
    }
}
