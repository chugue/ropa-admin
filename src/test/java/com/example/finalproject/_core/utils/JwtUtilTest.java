package com.example.finalproject._core.utils;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.SessionAdmin;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {

    @Test
    public void create_test(){

        Admin admin = Admin.builder()
                .id(1)
                .brandName("nike")
                .email("nike@naver.com")
                .role(Admin.AdminRole.BRAND)
                .build();

        String jwt = JwtUtill.create(admin);
        System.out.println("jwt : "+jwt);

        SessionAdmin sessionAdmin = JwtUtill.verify(jwt);

        System.out.println(sessionAdmin);
    }
}
