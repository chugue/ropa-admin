package com.example.finalproject.domain.admin;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionAdmin {
    private Integer id;
    private String username;
    private String email;
    private Admin.AdminRole role;

    @Builder
    public SessionAdmin(Integer id, String username, String email, Admin.AdminRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public SessionAdmin(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getBrandName();
        this.email = admin.getEmail();
        this.role = admin.getRole();
    }
}
