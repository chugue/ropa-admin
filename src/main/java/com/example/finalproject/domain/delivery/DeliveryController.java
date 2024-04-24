package com.example.finalproject.domain.delivery;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class DeliveryController {
    private final HttpServletRequest request;
    private final HttpSession session;

    // 배송 관리 페이지
    @GetMapping("/api/delivery-manage")
    public String deliveryManage() {
        return "delivery/delivery-manage";
    }

}
