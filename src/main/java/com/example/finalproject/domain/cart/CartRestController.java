package com.example.finalproject.domain.cart;

import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        CartResponse.CartDTO requestDTO = cartService.getCartByUserId(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(requestDTO));
    }

    // 장바구니 추가
    @PostMapping("/app/carts/save")
    public ResponseEntity<?> CartSave(@RequestBody CartRequest.SaveDTO requestDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        cartService.save(requestDTO, sessionUser.getId(), requestDTO.getItems().getId());
        CartResponse.CartDTO reqDTO = cartService.getCartByUserId(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    // 장바구니 아이템 삭제
    @DeleteMapping("/app/carts/delete/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("cartItemId") Integer cartItemId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        cartService.deleteCartItem(sessionUser.getId(), cartItemId);
        CartResponse.CartDTO requestDTO = cartService.getCartByUserId(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(requestDTO));
    }
}
