package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private String uuidName; // 사진명 UUID 적용 된 이름 앱이랑 소통

    private String originalFileName;

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
    private Sort sort; // 구분 [사용자, 아이템, 코디, 브랜드]

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin; // 사용자 고유번호


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
    public Photo(Integer id, String uuidName, String originalFileName, String path, Boolean isMainPhoto, Sort sort, Admin admin, User user, Items items, Codi codi, Timestamp createdAt, Timestamp updateAt) {
        this.id = id;
        this.uuidName = uuidName;
        this.originalFileName = originalFileName;
        this.path = path;
        this.isMainPhoto = isMainPhoto;
        this.sort = sort;
        this.admin = admin;
        this.user = user;
        this.items = items;
        this.codi = codi;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public enum Sort {
        USER, ITEM, CODI, BRAND
    }




    // 경로에 있는 사진을 읽어서 base64로 전환
    public String toBase64(Photo photo){
        String currentDir = System.getProperty("user.dir");
        String relativePath = photo.getPath();
        String fullPath = currentDir + File.separator + relativePath;
        try {
            byte[] imageData = Files.readAllBytes(Paths.get(fullPath));
            return Base64.encodeBase64String(imageData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}