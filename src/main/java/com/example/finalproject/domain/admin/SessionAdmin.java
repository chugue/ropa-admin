package com.example.finalproject.domain.admin;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionAdmin {
    private Integer id;
    private String username;
    private String email;
    private String role; // String으로 변경

    @Builder
    public SessionAdmin(Integer id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role; // AdminRole enum에서 문자열로 변환하여 할당
    }
}
