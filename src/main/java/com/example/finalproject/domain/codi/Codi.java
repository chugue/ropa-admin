package com.example.finalproject.domain.codi;

import com.example.finalproject.domain.love.Love;
import com.example.finalproject.domain.photo.Photo;
import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

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

    private String title; // 코디 제목

    @Column(nullable = false)
    private String description; // 코디 설명

    @OneToMany(mappedBy = "codi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Photo> photos;

    @OneToMany(mappedBy = "codi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Love> loves;

    @CreationTimestamp
    private Timestamp createdAt; // 등록시간

    @UpdateTimestamp
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
