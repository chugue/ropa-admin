package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import lombok.Data;

public class AdminResponse {
    //관리자의 브랜드별 매출 목록보기
    @Data
    public static class AdminSalesListDTO {
        private String brandName;
        private String brandPhone;
        private String brandEmail;
        private Double totalOrderAmount;
        private Double fee;


        public AdminSalesListDTO(OrderHistory orderHistory) {
            this.brandName = orderHistory.getAdmin().getBrandName();
            this.brandPhone = orderHistory.getAdmin().getPhone();
            this.brandEmail = orderHistory.getAdmin().getEmail();
        }

        public AdminSalesListDTO(Double totalOrderAmount, Double fee) {
            this.totalOrderAmount = totalOrderAmount;
            this.fee = fee;
        }
    }


    //브랜드의 매출 목록
    @Data
    public static class BrandOrderHistoryListDTO {
        private Integer orderHistoryId;
        private Integer itemsId;
        private String itemsName;
        private Double totalPrice;
        private Integer totalQuantity;

        public BrandOrderHistoryListDTO(OrderHistory orderHistory) {
            this.orderHistoryId = orderHistory.getId();
            this.itemsId = orderHistory.getItems().getId();
            this.itemsName = orderHistory.getItems().getName();
            this.totalPrice = orderHistory.getTotalPrice();
            this.totalQuantity = orderHistory.getTotalQuantity();
        }
    }
}
