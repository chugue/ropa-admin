package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import lombok.Data;

public class AdminResponse {

    //브랜드의 매출 목록
    @Data
    public static class brandOrderHistoryListDTO {
        private Integer orderHistoryId;
        private Integer itemsId;
        private String itemsName;
        private Integer totalPrice;
        private Integer totalQuantity;

        public brandOrderHistoryListDTO(OrderHistory orderHistory) {
            this.orderHistoryId = orderHistory.getId();
            this.itemsId = orderHistory.getItems().getId();
            this.itemsName = orderHistory.getItems().getName();
            this.totalPrice = orderHistory.getOrderItemPrice();
            this.totalQuantity = orderHistory.getOrderItemQty();
        }
    }
}
