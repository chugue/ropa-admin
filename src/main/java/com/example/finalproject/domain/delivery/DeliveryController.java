package com.example.finalproject.domain.delivery;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.orderHistory.OrderHistoryResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DeliveryController {
    private final HttpSession session;
    private final DeliveryService deliveryService;

    // 배송 관리 페이지 (관리자)
    @GetMapping("/api/delivery-manage")
    public String deliveryManage(HttpServletRequest request) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionAdmin");
        List<OrderHistoryResponse.DeliveryListDTO> orderDeliveryList = deliveryService.findByOrderHistoryItemsAdminAndDelivery(sessionAdmin.getId());
        request.setAttribute("orderDeliveryList", orderDeliveryList);
        return "delivery/delivery-manage";
    }

}
