package com.example.finalproject.domain.order;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final HttpServletRequest request;
    private final HttpSession session;

    // 주문 관리 페이지
    @GetMapping("/api/order-manage")
    public String orderManage() {
        return "order/order-manage";
    }

    // 주문 상세 페이지
    @GetMapping("/api/order-detail")
    public String orderDetail() {
        return "order-history";
    }

}
