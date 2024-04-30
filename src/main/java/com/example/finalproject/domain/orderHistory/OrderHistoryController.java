package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.admin.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderHistoryController {

    private final HttpSession session;
    private final OrderHistoryService orderHistoryService;


    // 주문 목록 페이지
    @GetMapping("/api/order-manage")
    public String orderManage(HttpServletRequest request) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionAdmin");
        List<OrderHistoryResponse.orderListDTO> orderHistoryList = orderHistoryService.findByOrderHistoryItemsAdmin(1);
        request.setAttribute("orderHistoryList", orderHistoryList);
        return "order/order-manage";
    }

    // 주문 상세 페이지
    @GetMapping("/api/order-history")
    public String orderHistory() {
        return "order/order-history";
    }

}
