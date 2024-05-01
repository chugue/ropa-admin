package com.example.finalproject.domain.items;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.category.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "items_tb")
@Data
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private String name; // 아이템명

    private String description; // 아이템 설명

    @Column(nullable = false)
    private String size; // 사이즈

    @Column(nullable = false)
    private Integer price; // 가격

    private Integer discountPrice; // 할인가

    @Column(nullable = false)
    private Integer stock; // 재고

    @CreationTimestamp
    private Timestamp createdAt; // 등록시간

    @UpdateTimestamp
    private Timestamp updatedAt; // 수정시간

    @Builder
    public Items(Integer id, Admin admin, String name, String description, String size, Integer price, Integer discountPrice, Integer stock, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.admin = admin;
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
        this.discountPrice = discountPrice;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}