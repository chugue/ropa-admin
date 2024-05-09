package com.example.finalproject.domain.admin;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminRequest {

    //로그인
    @Data
    public static class LoginDTO {
        private String email;
        private String password;
    }

    //회원가입
    @Data
    public static class JoinDTO {
        private String email; //아이디
        private String password; //비밀번호
        private String brandName; //브랜드 명
        private Admin.AdminRole role; // 관리자 / 브랜드
        private String phone; // 사업자 번호 (관리자, 브랜드)
        private String address; //주소
        private String businessNum; // 사업자 번호 (관리자, 브랜드)
        private Timestamp createdAt; // 브랜드, 관리자 회원가입 시간
        private MultipartFile brandImage;

        public JoinDTO(String email, String password, String brandName, Admin.AdminRole role, String phone, String address, String businessNum, Timestamp createdAt, MultipartFile brandImage) {
            this.email = email;
            this.password = password;
            this.brandName = brandName;
            this.role = role;
            this.phone = phone;
            this.address = address;
            this.businessNum = businessNum;
            this.createdAt = createdAt;
            this.brandImage = brandImage;
        }

        public Admin toAdminEntity(){
            return Admin.builder()
                    .email(email)
                    .password(password)
                    .brandName(brandName)
                    .role(Admin.AdminRole.ADMIN)
                    .phone(phone)
                    .address(address)
                    .businessNum(businessNum)
                    .createdAt(createdAt)
                    .build();
        }

        public Admin toBrandEntity(){
            return Admin.builder()
                    .email(email)
                    .password(password)
                    .brandName(brandName)
                    .role(Admin.AdminRole.BRAND)
                    .phone(phone)
                    .address(address)
                    .businessNum(businessNum)
                    .createdAt(createdAt)
                    .build();
        }
    }
}
