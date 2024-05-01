package com.example.finalproject.domain.user;

import lombok.Data;

public class UserResponse {

    @Data  // 로그인 성공시 응답 DTO
    public static class LoginDTO {
        private Integer id;
        private String email;
        private String username;

        public LoginDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getMyName();
        }
    }
}
