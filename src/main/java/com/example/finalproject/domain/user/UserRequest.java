package com.example.finalproject.domain.user;

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
        private String email;
        private String nickName;
        private String password;
        private Timestamp createdAt;

        @Builder
        public JoinDTO(String email, String nickName, String password) {
            this.email = email;
            this.nickName = nickName;
            this.password = password;
            this.createdAt = Timestamp.from(Instant.now());;
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
}
