package com.example.finalproject.domain.order;

import com.example.finalproject.domain.cart.Cart;
import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class OrderRequest {

    // 주문하기
    @Data
    public static class saveDTO {
        private User user;
        private List<Cart> cartItems;

        public saveDTO(User user, List<Cart> cartItems) {
            this.user = user;
            this.cartItems = cartItems;
        }

        public static Order toEntity(User user, List<Cart> cartItems) {
            int totalOrderAmount = 0;
            for (Cart cartItem : cartItems) {
                totalOrderAmount += cartItem.getTotalAmount();
            }

            // 주문 생성
            return Order.builder()
                    .user(user)
                    .purchaseAmount(totalOrderAmount)
                    .orderDate(new Timestamp(System.currentTimeMillis()))
                    .build();
        }
    }
}
