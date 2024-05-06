package com.example.finalproject.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class CartResponse {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CartDTO {
        private Integer userId;
        private List<CartListDTO> cartList;
        private Integer totalCartPrice;
    }

    @Data
    public static class CartListDTO {
        private Integer itemId;
        private String itemName;
        private String itemPhotoPath;
        private Integer itemPrice;
        private Integer quantity;
        private Integer totalItemPrice;

        public CartListDTO(Cart cart) {
            this.itemId = cart.getItems().getId();
            this.itemName = cart.getItems().getName();
            this.itemPhotoPath = cart.getItems().getPhotos().get(0).getPath();
            this.itemPrice = cart.getItems().getPrice();
            this.quantity = cart.getQuantity();
            this.totalItemPrice = itemPrice * quantity;
        }
    }
}
