package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.util.Objects;

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
            this.totalPrice = orderHistory.getTotalPrice();
            this.totalQuantity = orderHistory.getTotalQuantity();
        }
    }

    // 유저 목록
    @Data
    public static class UserListDTO {
        private Integer userId;
        private String myName;
        private String email; // 아이디
        private String mobile; // 연락처
        private String nickName; // 닉네임
        private String instagram; // 인스타그램
        private String status; // 크리에이터 지원 현황
        private Boolean isBlueChecked; // 크리에이터 인증

        public UserListDTO(User user) {
            this.userId = user.getId();
            this.myName = user.getMyName();
            this.email = user.getEmail();
            this.mobile = user.getMobile();
            this.nickName = user.getNickName();
            this.instagram = user.getInstagram();
            this.status = user.getStatus();
            this.isBlueChecked = user.getBlueChecked();

            if (!Objects.equals(user.getStatus(), "승인")) {
                this.isBlueChecked = false;
            }
        }
    }
}
