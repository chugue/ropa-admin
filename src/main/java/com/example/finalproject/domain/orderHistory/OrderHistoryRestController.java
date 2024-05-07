package com.example.finalproject.domain.orderHistory;

import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderHistoryRestController {
    private final OrderHistoryService orderHistoryService;
    private final HttpSession session;

    // 주문 목록 페이지
    @GetMapping("/app/orderHistories")
    public ResponseEntity<?> OrderHistories() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        OrderHistoryResponse.UserOrderHistoryDTO orderHistoryList = orderHistoryService.getOrderHistoryByUserId(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(orderHistoryList));
    }
}
