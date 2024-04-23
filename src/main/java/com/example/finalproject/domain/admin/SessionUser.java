package com.example.finalproject.domain.admin;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {
    private Integer id;
    private String username;

    @Builder
    public SessionUser(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
}


