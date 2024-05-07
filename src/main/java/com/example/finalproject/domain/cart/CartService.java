package com.example.finalproject.domain.cart;

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
}
