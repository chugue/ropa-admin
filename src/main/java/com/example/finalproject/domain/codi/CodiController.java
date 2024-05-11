package com.example.finalproject.domain.codi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CodiController {
    private final CodiService codiService;

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
