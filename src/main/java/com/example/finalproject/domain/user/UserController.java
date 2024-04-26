package com.example.finalproject.domain.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpServletRequest request;

    // 로그인
    @GetMapping({"/", "/login"})
    public String login() {
        return "admin/login-form";
    }

    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "admin/join-form";
    }
}
