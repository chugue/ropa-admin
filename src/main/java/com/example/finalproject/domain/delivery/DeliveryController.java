package com.example.finalproject.domain.delivery;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.orderHistory.OrderHistoryResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DeliveryController {
    private final HttpSession session;
    private final DeliveryService deliveryService;

    // 배송 관리 페이지 (브랜드)
    @GetMapping("/api/delivery-manage")
    public String deliveryManage(String searchBy, @RequestParam(defaultValue = "") String keyword, HttpServletRequest request) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        List<OrderHistoryResponse.DeliveryList> orderDeliveryList = deliveryService.findByOrderHistoryItemsAdminAndDelivery(sessionBrand.getId(), searchBy, keyword);
        request.setAttribute("orderDeliveryList", orderDeliveryList);
        return "delivery/delivery-manage";
    }

}