package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import lombok.Data;

public class AdminResponse {
    //관리자의 브랜드별 매출 목록보기
    @Data
    public static class SalesListDTO {
        private AdminDTO admin;
        private Long orderItemPrice;
        private Long fee;

        public SalesListDTO(Admin admin, Long orderItemPrice, Long fee) {
            this.admin = new AdminDTO(admin);
            this.orderItemPrice = orderItemPrice;
            this.fee = fee;
        }

        @Data
        public class AdminDTO {
            private Integer adminId;
            private String brandName;
            private String brandPhone;
            private String brandEmail;

            public AdminDTO(Admin admin) {
                this.adminId = admin.getId();
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
        private Integer totalPrice;
        private Integer totalQuantity;

        public BrandOrderHistoryListDTO(OrderHistory orderHistory) {
            this.orderHistoryId = orderHistory.getId();
            this.itemsId = orderHistory.getItems().getId();
            this.itemsName = orderHistory.getItems().getName();
            this.totalPrice = orderHistory.getOrderItemPrice();
            this.totalQuantity = orderHistory.getOrderItemQty();
        }
    }
}
