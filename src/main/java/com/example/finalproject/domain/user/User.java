package com.example.finalproject.domain.user;

import com.example.finalproject.domain.photo.Photo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    private String addressDetail; // 상세주소

    private String mobile; // 연락처

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Photo photo;

    private String height; // 키

    private String weight; // 체중

    private String job; // 직업 (직장인/학생)

    private String introMsg; // 크리에이터 한줄 자기소개

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

    @Column(name = "applyTime")
    private LocalDateTime applyTime; // 크리에이터 지원 시간

    @Builder
    public User(Integer id, String email, String password, String nickName, String myName, String address, String mobile, Photo photo, String height, String weight, String job, String introMsg, String instagram, Integer mileage, Boolean blueChecked, String status, Timestamp createdAt, Timestamp updateAt, LocalDateTime applyTime) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.myName = myName;
        this.address = address;
        this.mobile = mobile;
        this.photo = photo;
        this.height = height;
        this.weight = weight;
        this.job = job;
        this.introMsg = introMsg;
        this.instagram = instagram;
        this.mileage = mileage;
        this.blueChecked = blueChecked;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.applyTime = applyTime;
    }
//    private Integer userId;
//    @NotEmpty(message = "이메일이 공백일 수 없습니다.")
//    @Size(min = 1, max = 20, message = "이메일은 최소
//            @Email(message = "올바른 이메일 형식이어야 합니다")
//            private String email;
//            @NotEmpty(message = "실명이 공백일 수 없습니다.")
//            @Size(min = 1, max = 20, message = "닉네임은 최소
//            private String myName;
//            @NotEmpty(message = "닉네임이 공백일 수 없습니다.")
//            @Size(min = 1, max = 20, message = "닉네임은 최소
//            private String nickName;
//            private String mobile;
//            @NotEmpty(message = "비밀번호가 공백일 수 없습니다.")
//            @Size(min = 4, max = 20, message = "비밀번호는 최
//            private String password;
//            private PhotoDTO photo;

    public User toUpdate(UserRequest.ProfileUpdateDTO reqDTO) {
        this.myName = reqDTO.getMyName();
        this.nickName = reqDTO.getNickName();
        this.password = reqDTO.getPassword();
        this.mobile = reqDTO.getMobile();

        return this;
    }
}
