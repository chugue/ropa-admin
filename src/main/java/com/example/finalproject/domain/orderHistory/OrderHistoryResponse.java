package com.example.finalproject.domain.orderHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

public class OrderHistoryResponse {

    //브래드별 매출 목록보기
    @AllArgsConstructor
    @Data
    public static class SalesListDTO {
        private String brandName;
        private String b;
        private String email;
        private Integer totalPrice;
        private Integer


    }

}
