package com.example.finalproject.domain.cart;

import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.items.ItemsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ItemsRepository itemsRepository;

    // 사용자 장바구니 목록 보기
    public CartResponse.CartDTO getCartByUserId(Integer userId) {
        List<Cart> carts = cartRepository.findAllByUserId(userId);
        List<CartResponse.CartListDTO> cartListDTO = carts.stream()
                .map(CartResponse.CartListDTO::new).collect(Collectors.toList());
        Integer totalCartPrice = cartListDTO.stream().mapToInt(CartResponse.CartListDTO::getTotalItemPrice).sum();
        return new CartResponse.CartDTO(userId, cartListDTO, totalCartPrice);
    }

    // 사용자 장바구니 추가
    @Transactional
    public void save(CartRequest.SaveDTO saveDTO, Integer userId, Integer itemId) {
        // 아이템 ID를 기반으로 아이템 조회
        Items items = itemsRepository.findById(itemId).orElse(null);

        if (items != null) {
            // 이미 장바구니에 있는지 확인
            Cart IsCartItem = cartRepository.findByUserAndItem(userId, itemId);
            if (IsCartItem != null) {
                // 수량 증가
                IsCartItem.setQuantity(saveDTO.getQuantity());
                // 총 금액 업데이트
                IsCartItem.setTotalAmount((items.getPrice() * saveDTO.getQuantity()));
                // 장바구니에 있는 아이템 업데이트
                cartRepository.save(IsCartItem);
            } else {
                // 새로운 아이템을 장바구니에 추가
                cartRepository.save(saveDTO.toEntity());
            }
        }
    }

    // 사용자 장바구니에 있는 하나의 아이템 삭제
    @Transactional
    public void deleteCartItem(Integer userId, Integer cartItemId) {
        // 해당 아이템을 장바구니에서 찾음
        Cart cartItem = cartRepository.findById(cartItemId).orElse(null);

        // 장바구니에 해당 아이템이 존재하는지 확인
        if (cartItem != null && cartItem.getUser().getId().equals(userId)) {
            cartRepository.delete(cartItem);
        } else {
            // 사용자의 장바구니에 해당 아이템이 없거나, 장바구니에 접근할 수 없는 경우
            throw new Exception404("해당 사용자의 장바구니에 해당 아이템이 없습니다.");
        }
    }

    // 사용자 장바구니의 모든 아이템 삭제
    @Transactional
    public void clearCart(Integer userId) {
        // 해당 사용자의 모든 장바구니 아이템을 조회
        List<Cart> userCartItems = cartRepository.findAllByUserId(userId);
        cartRepository.deleteAll(userCartItems);
    }
}
