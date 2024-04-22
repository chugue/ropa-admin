package com.example.finalproject.domain.user;

import jakarta.persistence.*;
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

    private String username; // 아이디
    private String password; // 비밀 번호
    private String myName; //실명
    private String nickName; //별명
    private String height; // 키
    private String weight; // 체중
    private String address; //주소
    private String mobile; //휴대폰 번호
    private String email; //이메일
    private Boolean blueChecked; // true -> 인플루언서, false -> 일반 회원
    private Timestamp createdAt; //회원 가입한 시간
    private Timestamp updatedAt; //회원 정보 수저한 시간

}
