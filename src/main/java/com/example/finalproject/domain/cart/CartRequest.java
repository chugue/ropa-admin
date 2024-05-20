package com.example.finalproject.domain.cart;

import jakarta.persistence.Column;
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

        private Integer codiId;

        public SaveDTO(Integer itemId, Integer quantity, Integer codiId) {
            this.itemId = itemId;
            this.quantity = quantity;
            this.codiId = codiId;
        }
    }
}


