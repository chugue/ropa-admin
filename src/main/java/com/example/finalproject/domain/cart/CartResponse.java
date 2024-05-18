package com.example.finalproject.domain.cart;

import com.example.finalproject.domain.photo.Photo;
import lombok.Data;

import java.util.List;

public class CartResponse {

    @Data
    public static class Saved{
        private Integer cartId;
        private Integer itemId;
        private String itemName;
        private String photoPath;
        private Integer itemPrice;
        private Integer quantity;
        private Integer totalItemPrice;

        public Saved(Cart cart, Photo photo) {
            this.cartId = cart.getId();
            this.itemId = cart.getItems().getId();
            this.itemName = cart.getItems().getName();
            this.photoPath = photo.getPath();
            this.itemPrice = cart.getItems().getPrice();
            this.quantity = cart.getQuantity();
            this.totalItemPrice = itemPrice * quantity;
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
        private String photoPath;
        private Integer itemPrice;
        private Integer quantity;
        private Integer totalItemPrice;

        public CartList(Cart cart) {
            this.cartId = cart.getId();
            this.itemId = cart.getItems().getId();
            this.itemName = cart.getItems().getName();
            this.photoPath = cart.getItems().getPhotos().getFirst().getPath();
            this.itemPrice = cart.getItems().getPrice();
            this.quantity = cart.getQuantity();
            this.totalItemPrice = itemPrice * quantity;
        }
    }
}
