package com.example.finalproject.domain.cart;

import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.codi.CodiRepository;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.items.ItemsRepository;
import com.example.finalproject.domain.photo.Photo;
import com.example.finalproject.domain.photo.PhotoRepository;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ItemsRepository itemsRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final CodiRepository codiRepository;

    // 사용자 장바구니 목록 보기
    public CartResponse.CartInfo getCartByUserId(Integer userId) {
        List<Cart> carts = cartRepository.findAllByUserId(userId);
        List<CartResponse.CartList> cartListDTO = carts.stream()
                .map(CartResponse.CartList::new).collect(Collectors.toList());
        Integer totalCartPrice = cartListDTO.stream().mapToInt(CartResponse.CartList::getTotalItemPrice).sum();
        return new CartResponse.CartInfo(userId, cartListDTO, totalCartPrice);
    }

    // 사용자 장바구니 추가
    @Transactional
    public CartResponse.Saved save(CartRequest.SaveDTO reqDTO, Integer userId) {

        // 이미 장바구니에 있는지 확인
        Cart IsCartItem = cartRepository.findByUserAndItem(userId, reqDTO.getItemId()).orElse(null);
        Optional<Photo> photo = photoRepository.findByItemsId(reqDTO.getItemId());
        if (IsCartItem != null) {
            // 수량 증가
            IsCartItem.setQuantity(IsCartItem.getQuantity() + reqDTO.getQuantity());
            // 총 금액 업데이트
            IsCartItem.setTotalAmount((IsCartItem.getItems().getPrice() * IsCartItem.getQuantity()));
            if (reqDTO.getCodiId() != null) {
                IsCartItem.setCodi(codiRepository.findById(reqDTO.getCodiId()).orElse(null));
            }
            // 장바구니에 있는 아이템 업데이트
            Cart cart = cartRepository.save(IsCartItem);
            return new CartResponse.Saved(cart, photo.get());

        } else {
            // 새로운 아이템을 장바구니에 추가
            User user = userRepository.findById(userId).orElseThrow(() -> new Exception404("사용자 정보를 찾을 수 없습니다."));
            Items items = itemsRepository.findById(reqDTO.getItemId()).orElseThrow(() -> new Exception404("아이템을 찾을 수 없습니다."));
            Codi codi = null;
            if (reqDTO.getCodiId() != null) {
                codi = codiRepository.findById(reqDTO.getCodiId()).orElse(null);
            }
            Cart cart = cartRepository.save(Cart.builder()
                    .user(user)
                    .items(items)
                    .codi(codi)
                    .quantity(reqDTO.getQuantity())
                    .totalAmount(items.getPrice() * reqDTO.getQuantity())
                    .createdAt(Timestamp.from(Instant.now())).build());
            return new CartResponse.Saved(cart, photo.get());
        }
    }


    // 사용자 장바구니에 있는 하나의 아이템 삭제
    @Transactional
    public void deleteCartItem(Integer userId, Integer cartId) {
        // 해당 아이템을 장바구니에서 찾음
        Cart cartItem = cartRepository.findById(cartId).orElse(null);

        // 장바구니에 해당 아이템이 존재하는지 확인
        if (cartItem != null && cartItem.getUser().getId().equals(userId)) {
            cartRepository.delete(cartItem);
        } else {
            // 사용자의 장바구니에 해당 아이템이 없거나, 장바구니에 접근할 수 없는 경우
            throw new Exception404("해당 사용자의 장바구니에 해당 아이템이 없습니다.");
        }
    }

    // 사용자 장바구니의 모든 아이템 삭제
//    @Transactional
//    public void clearCart(Integer userId) {
//        // 해당 사용자의 모든 장바구니 아이템을 조회
//        List<Cart> userCartItems = cartRepository.findAllByUserId(userId);
//        cartRepository.deleteAll(userCartItems);
//    }
}
