package com.example.finalproject.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

public class UserRequest {

    //회원가입
    @NoArgsConstructor
    @Data
    public static class JoinDTO {
        private String myName;
        private String email;
        private String nickName;
        private String password;
        private String mobile;
        private String status;
        private Boolean blueChecked;
        private Timestamp createdAt;

        @Builder
        public JoinDTO(String myName ,String email, String nickName, String password, String mobile, Boolean blueChecked,String status) {
            this.myName = myName;
            this.email = email;
            this.nickName = nickName;
            this.password = password;
            this.mobile = mobile;
            this. status = status;
            this.blueChecked =blueChecked;
            this.createdAt = Timestamp.from(Instant.now());
            ;
        }
    }

    // 앱용 로그인 요청
    @NoArgsConstructor
    @Data
    public static class LoginDTO {
        private String email;
        private String password;

        @Builder
        public LoginDTO(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    // 앱 크리에이터 지원 요청
    @Data
    public static class CreatorApplyDTO {
        private String height;
        private String weight;
        private String instagram;
        private String comment;
        private String job;

        public CreatorApplyDTO(String height, String weight, String instagram, String comment, String job) {
            this.height = height;
            this.weight = weight;
            this.instagram = instagram;
            this.comment = comment;
            this.job = job;
        }
    }
}
