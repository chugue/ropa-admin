package com.example.finalproject.domain.inquiry;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "inquiry_tb")
@Data
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 유저 고유번호

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String content; // 내용

    @CreationTimestamp
    private Timestamp inquiryTime; // 문의 시간

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin; // 관리자 고유번호

    private String comment; // 답변

    private Timestamp commentTime; // 답변 시간

    @Column(nullable = false)
    private Boolean status; // 문의 상태

    @Builder
    public Inquiry(Integer id, User user, String title, String content, Timestamp inquiryTime, Admin admin, String comment, Timestamp commentTime, Boolean status) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.inquiryTime = inquiryTime;
        this.admin = admin;
        this.comment = comment;
        this.commentTime = commentTime;
        this.status = status;
    }
}
