package com.example.finalproject.domain.cart;

import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartRestController {
    private final CartService cartService;
    private final HttpSession session;

    // 장바구니 목록
    @GetMapping("/app/carts")
    public ResponseEntity<?> CartList() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        CartResponse.CartInfo requestDTO = cartService.getCartByUserId(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(requestDTO));
    }

    // 장바구니 추가
    @PostMapping("/app/carts/save")
    public ResponseEntity<?> CartSave(@Valid @RequestBody CartRequest.SaveDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        CartResponse.Saved respDTO = cartService.save(reqDTO, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 장바구니 아이템 삭제
    @DeleteMapping("/app/carts/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("cartItemId") Integer cartId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        cartService.deleteCartItem(sessionUser.getId(), cartId);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
