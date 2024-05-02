package com.example.finalproject.domain.category;

import com.example.finalproject.domain.items.Items;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "category_tb")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String main; // 카테고리명

    @Column
    private String sub; // 카테고리명

    @Builder
    public Category(Integer id, String main, String sub) {
        this.id = id;
        this.main = main;
        this.sub = sub;
    }
}
