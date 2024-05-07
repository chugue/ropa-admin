package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "photo_tb")
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name; // 사진명

    @Column(nullable = false)
    private String path; // 경로

    // 아이템이나 코디에서 사진이 여러장일때 대표사진 여부 체크
    // 아이템사진 구성 = 대표사진 + 디테일 사진 (이 경우 mainPhoto = false)
    // 코디 사진 구성 = 대표사진 + 다른 코디 사진들 (이 경우 mainPhoto = false)
    // 사용자 사진 = 1:1관계 이므로 전부 mainPhoto = true
    @Column(nullable = false,  columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isMainPhoto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sort sort; // 구분 [사용자, 아이템, 코디]

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 사용자 고유번호

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "items_id")
    private Items items; // 아이템 고유번호

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "codi_id")
    private Codi codi;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updateAt;

    @Builder
    public Photo(Integer id, String name, String path, Boolean isMainPhoto, Sort sort, User user, Items items, Codi codi, Timestamp createdAt, Timestamp updateAt) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.isMainPhoto = isMainPhoto;
        this.sort = sort;
        this.user = user;
        this.items = items;
        this.codi = codi;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public enum Sort {
        USER, ITEM, CODI
    }

}