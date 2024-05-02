package com.example.finalproject.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String email; // 아이디

    @Column(nullable = false)
    private String password; //비밀번호

    @Column(nullable = false)
    private String nickName; //별명

    private String myName; //실명

    private String address; //주소

    private String mobile; // 연락처

    private String height; // 키

    private String weight; // 체중

    private String introduction;

    private String instagram; // 인스타그램

    private Integer mileage; // 크리에이터의 마일리지

    @Column(nullable = false)
    private Boolean blueChecked; //true -> 크리에이터, false -> 일반 회원

    @Column(nullable = false)
    private String status; // 신청 상태: "신청 전", "승인 대기", "승인", "거절" 중 하나로 설정됨

    @CreationTimestamp
    private Timestamp createdAt; //가입시간

    @UpdateTimestamp
    private Timestamp updateAt; // 회원 수정 시간

    @Builder
    public User(Integer id, String email, String password, String nickName, String myName, String address, String mobile, String height, String weight, String instagram, Integer mileage, Boolean blueChecked, String status, Timestamp createdAt, Timestamp updateAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.myName = myName;
        this.address = address;
        this.mobile = mobile;
        this.height = height;
        this.weight = weight;
        this.instagram = instagram;
        this.mileage = mileage;
        this.blueChecked = blueChecked;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
}
