package com.example.finalproject.domain.love;

import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "love_tb")
@Data
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 유저 고유번호

    @ManyToOne
    @JoinColumn(name = "codi_id", nullable = false)
    private Codi codi; // 코디 고유번호

    @Column(nullable = false)
    private Boolean isLoved; // 좋아요 상태

    @Column(nullable = false)
    private Timestamp createdAt; // 생성시간

    @Builder
    public Love(Integer id, User user, Codi codi, Boolean isLoved, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.codi = codi;
        this.isLoved = isLoved;
        this.createdAt = createdAt;
    }
}
