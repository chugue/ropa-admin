package com.example.finalproject.domain.order;

import com.example.finalproject.domain.cart.Cart;
import com.example.finalproject.domain.photo.Photo;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

import static com.example.finalproject.domain.order.Order.DeliveryType.FREE;
import static com.example.finalproject.domain.order.Order.DeliveryType.ROCKET;

public class OrderResponse {

    @Data
    public static class PageView {
        private Integer orderId;
        private String name;
        private String phone;
        private String email;
        private String address;
        private String detailAddress;
        private String deliveryRequest;
        private Boolean isBaseAddress;
        private List<OrderCartInfo> cartInfos;
        private OrderInfo orderInfo;

        @Builder
        public PageView(Optional<Order> order, List<Cart> carts) {
            if (order.isEmpty()) {
                // Order가 null인 경우 기본 또는 빈 값을 할당
                this.orderId = null;
                this.name = "";
                this.phone = "";
                this.email = "";
                this.address = "";
                this.detailAddress = "";
                this.deliveryRequest = "";
                this.isBaseAddress = false;
                this.orderInfo = new OrderInfo(null, carts); // OrderInfo 객체 자체를 null로 둘 수도 있습니다.
            } else if (order.isPresent()) {
                this.orderId = order.get().getId();
                this.name = order.get().getDelivery().getRecipient();
                this.phone = order.get().getDelivery().getPhoneNumber();
                this.email = order.get().getUser().getEmail();
                this.address = order.get().getDelivery().getAddress();
                this.detailAddress = order.get().getDelivery().getAddressDetail();
                if (order.get().getDelivery().getRequestMsg() == null) {
                    this.deliveryRequest = "배송 요청사항 없음";
                } else {
                    this.deliveryRequest = order.get().getDelivery().getRequestMsg();
                }
                if (order.get().getDelivery().getIsBaseAddress() == null) {
                    this.isBaseAddress = false;
                } else {
                    this.isBaseAddress = order.get().getDelivery().getIsBaseAddress();
                }
                this.orderInfo = new OrderInfo(order.get(), carts);
            }
            this.cartInfos = carts.stream().map(OrderCartInfo::new).toList();

        }

        @Data
        public class OrderCartInfo {
            private Integer cartId;
            private Integer itemId;
            private String itemName;
            private String size;
            private Integer quantity;
            private Integer price;
            private Integer amount;
            private ItemPhoto itemPhoto;

            public OrderCartInfo(Cart cart) {
                this.cartId = cart.getId();
                this.itemId = cart.getItems().getId();
                this.itemName = cart.getItems().getName();
                this.size = cart.getItems().getSize();
                this.quantity = cart.getQuantity();
                this.price = cart.getItems().getPrice();
                this.amount = cart.getTotalAmount();
                this.itemPhoto = new ItemPhoto(cart.getItems().getPhotos().getFirst());
            }

            @Data
            public class ItemPhoto {
                private Integer photoId;
                private String photoTitle;
                private String photoPath;
                private Boolean isMainPhoto;

                public ItemPhoto(Photo photo) {
                    this.photoId = photo.getId();
                    this.photoTitle = photo.getName();
                    this.photoPath = photo.getPath();
                    this.isMainPhoto = photo.getIsMainPhoto();
                }
            }
        }

        @Data
        public class OrderInfo {
            private Integer orderAmount;
            private Integer deliveryFee;
            private Integer discount;
            private Integer purchaseAmount;
            private Order.PayMethod payMethod;
            private Boolean savedPayMethod;

            public OrderInfo(Order order, List<Cart> carts) {
                if (order == null) {
                    Integer calculation = 0;
                    for (Cart cartItem : carts) {
                        calculation += cartItem.getTotalAmount();
                    }
                    this.orderAmount = calculation;
                    this.deliveryFee = 0;
                    this.discount = 0;
                    this.purchaseAmount = calculation + this.deliveryFee;
                    this.payMethod = null;
                    this.savedPayMethod = false;
                } else {
                    Integer calculation = 0;
                    for (Cart cartItem : carts) {
                        calculation += cartItem.getTotalAmount();
                    }
                    this.orderAmount = calculation;
                    if (order.getDeliveryType() == FREE) {
                        this.deliveryFee = 0;
                    }
                    if (order.getDeliveryType() == ROCKET) {
                        this.deliveryFee = 3000;
                    }
                    this.discount = 0;
                    this.purchaseAmount = calculation + this.deliveryFee;
                    this.payMethod = order.getPayMethod();
                    this.savedPayMethod = order.getSavePayMethod();
                }
            }
        }
    }
}

