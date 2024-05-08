package com.example.finalproject.domain.cart;

import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.user.User;
import lombok.Data;

public class CartRequest {

    @Data
    public static class SaveDTO {
        private Integer userId;
        private Items items;
        private Integer quantity;
        private Integer totalAmount;

        public Cart toEntity() {
            User user = new User();
            user.setId(userId);
            return Cart.builder()
                    .user(user)
                    .items(items)
                    .quantity(quantity)
                    .totalAmount(items.getPrice() * quantity)
                    .build();
        }
    }
}


