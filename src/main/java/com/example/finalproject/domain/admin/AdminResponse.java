package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import lombok.Data;

public class AdminResponse {
    //관리자의 브랜드별 매출 목록보기
    @Data
    public static class AdminSalesListDTO {
        private AdminDTO admin;
        private Double totalPrice;
        private Double fee;

        public AdminSalesListDTO(Admin admin, Double totalPrice, Double fee) {
            this.admin = new AdminDTO(admin);
            this.totalPrice = totalPrice;
            this.fee = fee;
        }

        @Data
        public class AdminDTO {
            private String brandName;
            private String brandPhone;
            private String brandEmail;

            public AdminDTO(Admin admin) {
                this.brandName = admin.getBrandName();
                this.brandPhone = admin.getPhone();
                this.brandEmail = admin.getEmail();
            }
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
