package com.example.finalproject.domain.order;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject.domain.cart.Cart;
import com.example.finalproject.domain.cart.CartRepository;
import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    // 주문하기
    @Transactional
    public Order saveOrder(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("사용자의의 정보를 찾을 수 없습니다."));
        // 사용자의 장바구니에서 아이템들을 가져옴
        List<Cart> cartItems = cartRepository.findAllByUserId(userId);

        // 주문 생성
        Order order = OrderRequest.saveDTO.toEntity(user, cartItems);

        // 장바구니에서 주문내역으로 이동
        for (Cart cartItem : cartItems) {
            OrderHistory orderHistory = OrderHistory.builder()
                    .admin(cartItem.getItems().getAdmin())
                    .order(order)
                    .items(cartItem.getItems())
                    .orderItemQty(cartItem.getQuantity())
                    .orderItemPrice(cartItem.getTotalAmount())
                    .build();

            // 주문 내역 저장
            orderHistoryRepository.save(orderHistory);
        }

        // 주문 저장
        orderRepository.save(order);

        // 장바구니 비우기
        cartRepository.deleteAll(cartItems);

        return order;
    }
}
