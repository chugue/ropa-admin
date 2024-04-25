package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private sort sort; // 구분 [사용자, 아이템, 코디]
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 사용자 고유번호
    @ManyToOne
    @JoinColumn(name = "items_id", nullable = false)
    private Items items; // 아이템 고유번호
    @ManyToOne
    @JoinColumn(name = "codi_id", nullable = false)
    private Codi codi;
    @Column(nullable = false)
    private Timestamp createdAt;
    private Timestamp updateAt;
    public Photo(Integer id, String name, String path, Photo.sort sort, User user, Items items, Codi codi, Timestamp createdAt, Timestamp updateAt) {
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

    enum sort {
        USER, ITEM, CODI
    }
}
