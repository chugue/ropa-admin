package com.example.finalproject.domain.order;

import com.example.finalproject.domain.items.Items;
import lombok.Data;

import java.sql.Timestamp;

public class OrderResponse {
    @Data
    public static class orderListDTO {
        private Integer orderId;
        private String userName;
        private String userPhone;
        private Integer itemId;
        private String itemName;
        private String price;
        private Integer count;
        private Integer totalPrice;
        private Timestamp orderDate;

        public orderListDTO(Order order, Items items) {
            this.orderId = order.getId();
            this.userName = order.getUser().getMyName();
            this.userPhone = order.getUser().getMobile();
            this.itemId = items.getId();
            this.itemName = items.getName();
            this.price = items.getPrice();
            this.count = order.getOrderHistory().getTotalQuantity();
            this.totalPrice = order.getOrderHistory().getTotalPrice();
            this.orderDate = order.getOrderDate();
        }
    }
}
