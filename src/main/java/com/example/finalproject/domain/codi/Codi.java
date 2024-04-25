package com.example.finalproject.domain.codi;

import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "codi_tb")
@Data
public class Codi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 사용자 고유번호

    @Column(nullable = false)
    private String title; // 코디 제목

    private String description; // 코디 설명

    @Column(nullable = false)
    private Timestamp createdAt; // 등록시간

    private Timestamp updatedAt; // 수정시간

    @Builder
    public Codi(Integer id, User user, String title, String description, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
