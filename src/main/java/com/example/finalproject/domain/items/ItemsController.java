package com.example.finalproject.domain.items;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ItemsController {
    private final HttpServletRequest request;
    private final HttpSession session;

    // 상품 관리 페이지
    @GetMapping("/api/items-manage")
    public String itemsManage() {
        return "items/items-manage";
    }

    // 상품 상세 페이지
    @GetMapping("/api/items-detail")
    public String itemsDetail() {
        return "items/items-detail";
    }

    // 상품 등록 폼
    @GetMapping("/api/items-register-form")
    public String itemsRegisterForm() {
        return "items/items-register-form";
    }

    // 상품 수정 폼
    @GetMapping("/api/items-update-form")
    public String itemsUpdateForm() {
        return "items/items-update-form";
    }

}
