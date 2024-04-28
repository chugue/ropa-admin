package com.example.finalproject.domain.order;

import lombok.Data;

import java.sql.Timestamp;

public class OrderResponse {
    @Data
    public static class orderListDTO {
        public Integer orderId;
        public String userName;
        public String userPhone;
        public Integer totalOrderAmount;
        public Timestamp orderDate;

        public orderListDTO(Order order) {
            this.orderId = order.getId();
            this.userName = order.getUser().getMyName();
            this.userPhone = order.getUser().getMobile();
            this.totalOrderAmount = order.getTotalOrderAmount();
            this.orderDate = order.getOrderDate();
        }
    }
}
