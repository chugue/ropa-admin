package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.photo.Photo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "admin_tb")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String email; //아이디

    @Column(nullable = false)
    private String password; //비밀번호

    private String brandName; //브랜드 명

    private String phone; //브랜드 전화 번호

    @Enumerated(EnumType.STRING)
    private AdminRole role; // 관리자 / 브랜드

    public enum AdminRole {
        ADMIN, BRAND;

    }

    @Column(nullable = false)
    private String address; //주소

    private String businessNum; // 사업자 번호 (관리자, 브랜드)

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Photo photo;

    @CreationTimestamp
    private Timestamp createdAt; // 브랜드, 관리자 회원가입 시간


    private Timestamp updateAt; // 관리자 / 브랜드 수정 날짜

    @Builder
    public Admin(Integer id, String email, String password, String brandName, String phone, AdminRole role, String address, String businessNum, Photo photo, Timestamp createdAt, Timestamp updateAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.brandName = brandName;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.businessNum = businessNum;
        this.photo = photo;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
}
