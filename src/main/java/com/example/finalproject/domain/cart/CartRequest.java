package com.example.finalproject.domain.cart;

import lombok.Data;

public class CartRequest {

    @Data
    public static class SaveDTO {
        private Integer itemId;
        private Integer quantity;

        public SaveDTO(Integer itemId, Integer quantity) {
            this.itemId = itemId;
            this.quantity = quantity;
        }

//        public Cart toEntity() {
//            User user = new User();
//            user.setId(userId);
//            return Cart.builder()
//                    .user(user)
//                    .items(items)
//                    .quantity(quantity)
//                    .totalAmount(items.getPrice() * quantity)
//                    .build();
//        }
    }
}


