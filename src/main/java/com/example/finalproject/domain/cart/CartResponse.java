package com.example.finalproject.domain.cart;

import lombok.Data;

import java.util.List;

public class CartResponse {

    // 장바구니 DTO
    @Data
    public static class CartDTO {
        private Integer userId;
        private List<CartListDTO> cartList;
        private Integer totalCartPrice;

        public CartDTO(Integer userId, List<CartListDTO> cartList, Integer totalCartPrice) {
            this.userId = userId;
            this.cartList = cartList;
            this.totalCartPrice = totalCartPrice;
        }
    }

    // 장바구니 아이템 DTO
    @Data
    public static class CartListDTO {
        private Integer cartId;
        private Integer itemId;
        private String itemName;
        private String itemPhotoPath;
        private Integer itemPrice;
        private Integer quantity;
        private Integer totalItemPrice;

        public CartListDTO(Cart cart) {
            this.cartId = cart.getId();
            this.itemId = cart.getItems().getId();
            this.itemName = cart.getItems().getName();
            this.itemPhotoPath = cart.getItems().getPhotos().getFirst().getPath();
            this.itemPrice = cart.getItems().getPrice();
            this.quantity = cart.getQuantity();
            this.totalItemPrice = itemPrice * quantity;
        }
    }
}
