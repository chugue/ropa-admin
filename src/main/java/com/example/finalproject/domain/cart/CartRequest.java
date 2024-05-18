package com.example.finalproject.domain.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CartRequest {

    @Data
    @NoArgsConstructor
    public static class SaveDTO {
        @NotNull(message = "상품을 정해 주셔야 합니다.")
        private Integer itemId;

        @NotNull(message = "수량을 정해 주셔야 합니다.")
        private Integer quantity;

        public SaveDTO(Integer itemId, Integer quantity) {
            this.itemId = itemId;
            this.quantity = quantity;
        }
    }
}


