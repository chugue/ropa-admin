package com.example.finalproject.domain.order;

import com.example.finalproject.domain.cart.Cart;
import com.example.finalproject.domain.delivery.Delivery;
import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.photo.Photo;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

import static com.example.finalproject.domain.order.Order.DeliveryType.FREE;
import static com.example.finalproject.domain.order.Order.PayMethod.NA;

public class OrderResponse {

    @Data
    public static class SaveOrder {
        private SavedOrder savedOrder;
        private SavedDelievery savedDelievery;
        private List<DeletedCart> deletedCarts;
        private List<SavedOH> savedOHList;

        public SaveOrder(Order order, Delivery delivery, List<Cart> carts, List<OrderHistory> orderHistories) {
            this.savedOrder = new SavedOrder(order);
            this.savedDelievery = new SavedDelievery(delivery);
            this.deletedCarts = carts.stream().map(DeletedCart::new).toList();
            this.savedOHList = orderHistories.stream().map(SavedOH::new).toList();
        }

        @Data
        public class SavedOrder {
            private Integer orderId;
            private Integer userId;

            public SavedOrder(Order order) {
                this.orderId = order.getId();
                this.userId = order.getUser().getId();
            }
        }

        @Data
        private class SavedDelievery {
            private Integer deliveryId;
            private String recipient;
            private String status;
            private Timestamp startDate;

            public SavedDelievery(Delivery delivery) {
                this.deliveryId = delivery.getId();
                this.recipient = delivery.getRecipient();
                this.status = delivery.getStatus();
                this.startDate = delivery.getStartDate();
            }
        }

        @Data
        private class DeletedCart {
            private Integer cartId;
            private Integer itemId;

            public DeletedCart(Cart cart) {
                this.cartId = cart.getId();
                this.itemId = cart.getItems().getId();
            }
        }

        @Data
        private class SavedOH {
            private Integer orderHistoryId;
            private Integer adminId;
            private Integer orderId;
            private Integer itemsId;

            public SavedOH(OrderHistory orderHistory) {
                this.orderHistoryId = orderHistory.getId();
                this.adminId = orderHistory.getAdmin().getId();
                this.orderId = orderHistory.getOrder().getId();
                this.itemsId = orderHistory.getItems().getId();
            }
        }
    }

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
        public PageView(List<Order> order, List<Cart> carts) {
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
                // OrderInfo 객체 자체를 null로 둘 수도 있습니다.
            } else if (order.getFirst().getDelivery().getIsBaseAddress() != null) {
                if (!order.getFirst().getDelivery().getIsBaseAddress()) {
                    this.orderId = null;
                    this.name = "";
                    this.phone = "";
                    this.email = "";
                    this.address = "";
                    this.detailAddress = "";
                    this.deliveryRequest = "";
                    this.isBaseAddress = false;
                }
                this.orderId = order.getFirst().getId();
                this.name = order.getFirst().getDelivery().getRecipient();
                this.phone = order.getFirst().getDelivery().getPhoneNumber();
                this.email = order.getFirst().getUser().getEmail();
                this.address = order.getFirst().getDelivery().getAddress();
                this.detailAddress = order.getFirst().getDelivery().getAddressDetail();
                if (order.getFirst().getDelivery().getDeliveryRequest() == null) {
                    this.deliveryRequest = "배송 요청사항 없음";
                } else {
                    this.deliveryRequest = order.getFirst().getDelivery().getDeliveryRequest();
                }
                if (order.getFirst().getDelivery().getIsBaseAddress() == null) {
                    this.isBaseAddress = false;
                } else {
                    this.isBaseAddress = order.getFirst().getDelivery().getIsBaseAddress();
                }
                this.orderInfo = new OrderInfo(order.getFirst(), carts);

            } else {
                this.orderId = order.getFirst().getId();
                this.name = order.getFirst().getDelivery().getRecipient();
                this.phone = order.getFirst().getDelivery().getPhoneNumber();
                this.email = order.getFirst().getUser().getEmail();
                this.address = order.getFirst().getDelivery().getAddress();
                this.detailAddress = order.getFirst().getDelivery().getAddressDetail();
                if (order.getFirst().getDelivery().getDeliveryRequest() == null) {
                    this.deliveryRequest = "배송 요청사항 없음";
                } else {
                    this.deliveryRequest = order.getFirst().getDelivery().getDeliveryRequest();
                }
                if (order.getFirst().getDelivery().getIsBaseAddress() == null) {
                    this.isBaseAddress = false;
                } else {
                    this.isBaseAddress = order.getFirst().getDelivery().getIsBaseAddress();
                }
                this.orderInfo = new OrderInfo(order.getFirst(), carts);
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
                private String uuidName;
                private String base64;
                private Boolean isMainPhoto;

                public ItemPhoto(Photo photo) {
                    this.photoId = photo.getId();
                    this.uuidName = photo.getUuidName();
                    this.base64 = photo.toBase64(photo);
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
                Integer calculation = 0;
                for (Cart cartItem : carts) {
                    calculation += cartItem.getTotalAmount();
                }
                this.orderAmount = calculation;

                if (order == null) {
                    this.deliveryFee = 0;
                    this.discount = 0;
                    this.purchaseAmount = calculation + this.deliveryFee;
                    this.payMethod = NA;
                    this.savedPayMethod = false;
                } else {
                    if (order.getDeliveryType() == FREE) {
                        this.deliveryFee = 0;
                    } else {
                        this.deliveryFee = 3000;
                    }
                    this.discount = 0;
                    this.purchaseAmount = calculation + this.deliveryFee;

                    if (order.getSavePayMethod() != null && order.getSavePayMethod()) {
                        this.payMethod = order.getPayMethod();
                        this.savedPayMethod = order.getSavePayMethod();
                    } else {
                        this.payMethod = NA;
                        this.savedPayMethod = false;
                    }
                }
            }
        }
    }
}

