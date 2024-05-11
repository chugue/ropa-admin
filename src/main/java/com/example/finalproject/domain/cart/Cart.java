package com.example.finalproject.domain.cart;

import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "cart_tb")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 사용자 고유번호

    @ManyToOne
    @JoinColumn(name = "items_id", nullable = false)
    private Items items; // 아이템 고유번호

    @Column(nullable = false)
    private Integer quantity; // 아이템 개수

    @Column(nullable = false)
    private Integer totalAmount; // 아이템 총 금액

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Cart(Integer id, User user, Items items, Integer totalAmount, Integer quantity, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }
}
