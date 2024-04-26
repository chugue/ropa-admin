package com.example.finalproject.domain.codi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CodiController {
    private final HttpServletRequest request;
    private final HttpSession session;

    // 코디 관리 페이지
    @GetMapping("/api/codi-manage")
    public String codiManage() {
        return "codi/codi-manage";
    }

    // 코디 등록 페이지
    @GetMapping("/api/codi-register-form")
    public String codiRegisterForm() {
        return "codi/codi-register-form";
    }
}
