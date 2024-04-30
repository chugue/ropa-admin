package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.admin.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;

public class OrderHistoryResponse {
    //관리자의 브랜드별 매출 목록보기
    @Data
    public static class AdminSalesListDTO{
        private String adminName;
        private String adminPhone;
        private String adminEmail;

    }

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

}
