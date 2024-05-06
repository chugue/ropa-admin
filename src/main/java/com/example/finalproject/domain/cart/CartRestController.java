package com.example.finalproject.domain.cart;

import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.finalproject.domain.cart.CartResponse.*;

@RequiredArgsConstructor
@RestController
public class CartRestController {
    private final CartService cartService;
    private final HttpSession session;

    @GetMapping("/app/cart")
    public ResponseEntity<?> getCartByUserId() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        CartResponse.CartDTO requestDTO = cartService.getCartByUserId(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(requestDTO));
    }
}
