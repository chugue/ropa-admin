package com.example.finalproject.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String myName; //실명
    private String nickName; //별명
    private String height; //키
    private String weight; //체중
    private String address; //주소
    private String mobile; // 전화번호
    private String email; // 아이디
    private String password; //비밀번호
    private Boolean blueChecked; //true -> 크리에이터, false -> 일반 회원
    private Timestamp updateAt; // 회원 수정 시간
    private Timestamp createdAt; //가입시간

    @Builder
    public User(Integer id, String myName, String nickName, String height, String weight, String address, String mobile, String email, String password, Boolean blueChecked, Timestamp updateAt, Timestamp createdAt) {
        this.id = id;
        this.myName = myName;
        this.nickName = nickName;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.blueChecked = blueChecked;
        this.updateAt = updateAt;
        this.createdAt = createdAt;
    }
}
