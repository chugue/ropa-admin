package com.example.finalproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {
    private Integer id;
    private String username;
    private String email;
    private Boolean role;

    @Builder
    public SessionUser(Integer id, String username, String email, Boolean role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
