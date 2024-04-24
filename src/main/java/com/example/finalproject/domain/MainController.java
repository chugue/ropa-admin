package com.example.finalproject.domain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final HttpServletRequest request;
    private final HttpSession session;

//    // 메인 페이지 (대쉬보드??)
//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }

    // 관리자 매출관리 페이지
    @GetMapping("/api/admin-sales-manage")
    public String adminSalesManage() {
        return "sales/admin-sales-manage";
    }

    // 브랜드 매출관리 페이지
    @GetMapping("/api/brand-sales-manage")
    public String brandSalesManage() {
        return "sales/admin-sales-manage";
    }
}
