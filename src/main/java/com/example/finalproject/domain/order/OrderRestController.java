package com.example.finalproject.domain.order;

import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderRestController {
    private final OrderService orderService;
    private final HttpSession session;

    // 주문하기
    @PostMapping("/app/order")
    public ResponseEntity<?> OrderSave() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        orderService.saveOrder(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
