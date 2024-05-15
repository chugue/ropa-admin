package com.example.finalproject.domain.cart;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class CartRequest {

    @Data
    public static class SaveDTO {
        @NotEmpty(message = "상품을 정해 주셔야 합니다.")
        private Integer itemId;

        @NotEmpty(message = "수량을 정해 주셔야 합니다.")
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


