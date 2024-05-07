package com.example.finalproject.domain.cart;

import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
