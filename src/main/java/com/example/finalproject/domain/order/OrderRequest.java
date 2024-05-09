package com.example.finalproject.domain.order;

import com.example.finalproject.domain.cart.Cart;
import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class OrderRequest {

    // 주문하기
    @Data
    public static class SaveDTO {
        private User user;
        private List<Cart> cartItems;

        public SaveDTO(User user, List<Cart> cartItems) {
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

    @Data
    public static class SaveOrder {
        private String name;
        private String phone;
        private String email;
        private String postCode;
        private String address;
        private String detailAddress;
        private String deliveryRequest;
        private Boolean isBaseAddress;
        private PurchaseInfo purchaseInfo;

        public SaveOrder(String name, String phone, String email, String postCode, String address, String detailAddress, String deliveryRequest, Boolean isBaseAddress, PurchaseInfo purchaseInfo) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.postCode = postCode;
            this.address = address;
            this.detailAddress = detailAddress;
            this.deliveryRequest = deliveryRequest;
            this.isBaseAddress = isBaseAddress;
            this.purchaseInfo = purchaseInfo;
        }

        @Data
        public static class PurchaseInfo {
            private Integer orderAmount;
            private Order.DeliveryType deliveryType;
            private Integer deliveryFee;
            private Integer discount;
            private Integer purchaseAmount;
            private Order.PayMethod payMethod;
            private Boolean savedPayMethod;

            public PurchaseInfo(Integer orderAmount, Order.DeliveryType deliveryType, Integer deliveryFee, Integer discount, Integer purchaseAmount, Order.PayMethod payMethod, Boolean savedPayMethod) {
                this.orderAmount = orderAmount;
                this.deliveryType = deliveryType;
                this.deliveryFee = deliveryFee;
                this.discount = discount;
                this.purchaseAmount = purchaseAmount;
                this.payMethod = payMethod;
                this.savedPayMethod = savedPayMethod;
            }
        }
    }
}
