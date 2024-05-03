package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sort sort; // 구분 [사용자, 아이템, 코디]

    @ToString.Exclude
    @ManyToOne
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

    public Photo(Integer id, String name, String path, Photo.Sort sort, User user, Items items, Codi codi, Timestamp createdAt, Timestamp updateAt) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.sort = sort;
        this.user = user;
        this.items = items;
        this.codi = codi;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    enum Sort {
        USER, ITEM, CODI
    }
}
