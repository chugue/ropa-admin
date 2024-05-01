package com.example.finalproject.domain.user;

import lombok.Data;

import java.sql.Timestamp;

public class UserRequest {

    //회원가입
    @Data
    public static class JoinDTO {
        private String myName;
        private String nickName;
        private String height;
        private String weight;
        private String address;
        private String mobile;
        private String email;
        private String password;
        private Boolean blueChecked;
        private Timestamp updateAt;
        private Timestamp createdAt;
    }

    // 앱용 로그인 요청
    @Data
    public static class LoginDTO {
        private String email;
        private String password;

        public LoginDTO(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}
