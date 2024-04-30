package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.text.SimpleDateFormat;

public class OrderHistoryResponse {

    //브래드별 매출 목록보기
    @Data
    public static class SalesListDTO {
        private String brandName;
        private String b;
        private String email;
        private Integer totalPrice;
        private Double fee;

        public SalesListDTO(OrderHistory orderHistory) {
            this.brandName = orderHistory.getAdmin().getBrandName();
            this.b = b;
            this.email = email;
            this.totalPrice = totalPrice;
            this.fee = fee;
        }
    }

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
            this.count = orderHistory.getTotalQuantity();
            this.totalPrice = orderHistory.getTotalPrice();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.orderDate = dateFormat.format(orderHistory.getOrder().getOrderDate());
        }
    }

}
