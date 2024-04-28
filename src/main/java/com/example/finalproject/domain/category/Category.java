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

    @Column(nullable = false)
    private String name; // 카테고리명

    @ManyToOne
    @JoinColumn(name = "items_id", nullable = false)
    private Items items; // 아이템 고유번호

    @Builder
    public Category(Integer id, String name, Items items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }
}
