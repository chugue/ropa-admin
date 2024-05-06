package com.example.finalproject.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;

    // 사용자 장바구니 목록 보기
    public CartResponse.CartDTO getCartByUserId(Integer userId) {
        List<Cart> carts = cartRepository.findAllByUserId(userId);
        List<CartResponse.CartListDTO> cartListDTO = carts.stream()
                .map(CartResponse.CartListDTO::new).collect(Collectors.toList());
        Integer totalCartPrice = cartListDTO.stream().mapToInt(CartResponse.CartListDTO::getTotalItemPrice).sum();
        return new CartResponse.CartDTO(userId, cartListDTO, totalCartPrice);
    }
}
