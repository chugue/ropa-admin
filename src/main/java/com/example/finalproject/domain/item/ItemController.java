package com.example.finalproject.domain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ItemController {
    private final HttpServletRequest request;
    private final HttpSession session;

    // 상품 관리 페이지
    @GetMapping("/api/items-manage")
    public String itemsManage(){
        return "items/items-manage";
    }

}
