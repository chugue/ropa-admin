package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.util.Objects;

public class AdminResponse {
    //관리자의 브랜드별 매출 목록보기
    @Data
    public static class SalesList {
        private AdminInfo admin;
        private Long orderItemPrice;
        private Double fee;

        public SalesList(Admin admin, Long orderItemPrice, Double fee) {
            this.admin = new AdminInfo(admin);
            this.orderItemPrice = orderItemPrice;
            this.fee = fee;
        }

        @Data
        public class AdminInfo {
            private Integer adminId;
            private String brandName;
            private String brandPhone;
            private String brandEmail;

            public AdminInfo(Admin admin) {
                this.adminId = admin.getId();
                this.brandName = admin.getBrandName();
                this.brandPhone = admin.getPhone();
                this.brandEmail = admin.getEmail();
            }
        }
    }


    //브랜드의 매출 목록
    @Data
    public static class BrandOrderHistoryList {
        private Integer orderHistoryId;
        private Integer itemsId;
        private String itemsName;
        private Integer totalPrice;
        private Integer totalQuantity;

        public BrandOrderHistoryList(OrderHistory orderHistory) {
            this.orderHistoryId = orderHistory.getId();
            this.itemsId = orderHistory.getItems().getId();
            this.itemsName = orderHistory.getItems().getName();
            this.totalPrice = orderHistory.getOrderItemPrice();
            this.totalQuantity = orderHistory.getOrderItemQty();
        }
    }

    // 유저 목록
    @Data
    public static class UserList {
        private Integer userId;
        private String myName;
        private String email; // 아이디
        private String mobile; // 연락처
        private String nickName; // 닉네임
        private String instagram; // 인스타그램
        private String status; // 크리에이터 지원 현황
        private Boolean isBlueChecked; // 크리에이터 인증

        public UserList(User user) {
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

    //크리에이터 관리 페이지
    @Data
    public static class CreatorList {
        private Integer userId;
        private String myName;
        private String email; // 아이디
        private String mobile; // 연락처
        private String nickName; // 닉네임
        private String instagram; // 인스타그램
        private String status; // 크리에이터 지원 현황
        private Boolean isBlueChecked; // 크리에이터 인증
        public CreatorList(User user) {
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
