package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.delivery.Delivery;
import com.example.finalproject.domain.deliveryAddress.DeliveryAddress;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.text.SimpleDateFormat;

public class OrderHistoryResponse {

    @Data
    public static class orderListDTO {
        private Integer orderId;
        private String userName;
        private String userPhone;
        private Integer itemId;
        private String itemName;
        private Integer price;
        private Integer count;
        private Integer totalPrice;
        private String orderDate;

        public orderListDTO(OrderHistory orderHistory, User user, Items items) {
            this.orderId = orderHistory.getId();
            this.userName = user.getMyName();
            this.userPhone = user.getMobile();
            this.itemId = items.getId();
            this.itemName = items.getName();
            this.price = items.getPrice();
            this.count = orderHistory.getOrderItemQty();
            this.totalPrice = orderHistory.getOrderItemPrice().doubleValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.orderDate = dateFormat.format(orderHistory.getOrder().getOrderDate());
        }
    }

    @Data
    public static class DeliveryListDTO {
        private Integer orderId; // 주문 코드
        private String userName; // 주문자
        private String recipient; // 수령인
        private String recipientPhoneNumber; // 수령인 연락처
        private String status; // 배송현황
        private String orderDate; // 주문일자
        private String endDate; // 배송도착일자

        public DeliveryListDTO(OrderHistory orderHistory, User user, DeliveryAddress deliveryAddress, Delivery delivery) {
            this.orderId = orderHistory.getOrder().getId();
            this.userName = user.getMyName();
            this.recipient = deliveryAddress.getRecipient();
            this.recipientPhoneNumber = deliveryAddress.getPhoneNumber();
            this.status = delivery.getStatus();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.orderDate = dateFormat.format(delivery.getStartDate());
            this.endDate = dateFormat.format(delivery.getEndDate());
        }
    }
}
