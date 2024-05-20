package com.example.finalproject.domain.order;

import com.example.finalproject.domain.cart.Cart;
import com.example.finalproject.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

public class OrderRequest {

    @NoArgsConstructor
    @Data
    public static class OrderPage {
        private Integer itemId;
        private Integer codiId;

        public OrderPage(Integer itemId, Integer codiId) {
            this.itemId = itemId;
            this.codiId = codiId;
        }
    }

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

    @NoArgsConstructor
    @Data
    public static class SaveOrder {
        @NotEmpty(message = "주문자 이름은 공백 일 수 없습니다.")
        private String name;

        @NotEmpty(message = "전화번호가 공백일 수 없습니다")
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다")
        private String phone;

        @NotEmpty(message = "이메일이 공백일 수 없습니다")
        @Email(message = "올바른 이메일 형식이어야 합니다")
        private String email;

        @NotEmpty(message = "우편번호가 공백 일 수 없습니다")
        private String postCode;

        @NotEmpty(message = "주소가 공백일 수 없습니다.")
        private String address;

        @NotEmpty(message = "주소가 공백일 수 없습니다.")
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

        @NoArgsConstructor
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
