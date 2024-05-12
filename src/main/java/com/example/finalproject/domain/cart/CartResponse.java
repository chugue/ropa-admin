package com.example.finalproject.domain.cart;

import lombok.Data;

import java.util.List;

public class CartResponse {

    @Data
    public static class Saved{
        private Integer cartId;
        private Integer userId;
        private Integer itemId;

        public Saved(Cart cart) {
            this.cartId = cart.getId();
            this.userId = cart.getUser().getId();
            this.itemId = cart.getItems().getId();
        }
    }

    // 장바구니 DTO
    @Data
    public static class CartInfo {
        private Integer userId;
        private List<CartList> cartList;
        private Integer totalCartPrice;

        public CartInfo(Integer userId, List<CartList> cartList, Integer totalCartPrice) {
            this.userId = userId;
            this.cartList = cartList;
            this.totalCartPrice = totalCartPrice;
        }
    }

    // 장바구니 아이템 DTO
    @Data
    public static class CartList {
        private Integer cartId;
        private Integer itemId;
        private String itemName;
        private String itemPhotoBase64;
        private Integer itemPrice;
        private Integer quantity;
        private Integer totalItemPrice;

        public CartList(Cart cart) {
            this.cartId = cart.getId();
            this.itemId = cart.getItems().getId();
            this.itemName = cart.getItems().getName();
            this.itemPhotoBase64 = cart.getItems().getPhotos().getFirst().toBase64(cart.getItems().getPhotos().getFirst());
            this.itemPrice = cart.getItems().getPrice();
            this.quantity = cart.getQuantity();
            this.totalItemPrice = itemPrice * quantity;
        }
    }
}
