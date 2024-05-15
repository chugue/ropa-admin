package com.example.finalproject.domain.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminRequest {

    //로그인
    @Data
    public static class LoginDTO {
        @NotEmpty(message = "이메일이 공백일 수 없습니다")
        private String email;

        @NotEmpty(message = "비밀번호가 공백일 수 없습니다.")
        @Size(min = 4, max = 20, message = "비밀번호는 최소 4자 이상 최대 20자 이하여야 합니다.")
        private String password;
    }

    //회원가입
    @Data
    public static class JoinDTO {
        @NotEmpty(message = "이메일이 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "유저네임은 최소 1자 이상 최대 20자 이하여야 합니다.")
        private String email; //아이디

        @NotEmpty(message = "비밀번호가 공백일 수 없습니다.")
        @Size(min = 4, max = 20, message = "비밀번호는 최소 4자 이상 최대 20자 이하여야 합니다.")
        private String password; //비밀번호

        @NotEmpty(message = "브랜드 이름이 공백일 수 없습니다.")
        private String brandName; //브랜드 명

        @NotEmpty(message = "구분을 정해주셔야 합니다.")
        private Admin.AdminRole role; // 관리자 / 브랜드

        @NotEmpty(message = "전화번호가 공백일 수 없습니다")
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다")
        private String phone; // 사업자 번호 (관리자, 브랜드)

        @NotEmpty(message = "주소는 공백일 수 없습니다")
        private String address; //주소

        @NotEmpty(message = "사업자 번호는 공백일 수 없습니다")
        private String businessNum; // 사업자 번호 (관리자, 브랜드)
        private Timestamp createdAt; // 브랜드, 관리자 회원가입 시간

        @NotEmpty(message = "사진은 공백일 수 없습니다")
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
