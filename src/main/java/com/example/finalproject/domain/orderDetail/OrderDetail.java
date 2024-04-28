package com.example.finalproject.domain.orderDetail;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.items.Items;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "orderdetail_tb")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Items items; // 아이템 고유번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin; // 관리자 고유번호

    @Column(nullable = false)
    private Integer totalQuantity; // 상품의 총 개수

    @Column(nullable = false)
    private Integer totalPrice; // 상품의 총 가격

    @Builder
    public OrderDetail(Integer id, Items items, Admin admin, Integer totalQuantity, Integer totalPrice) {
        this.id = id;
        this.items = items;
        this.admin = admin;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }
}
