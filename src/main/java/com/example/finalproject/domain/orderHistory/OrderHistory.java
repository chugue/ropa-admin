package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "orderhistory_tb")
@Data
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Items items; // 아이템 고유번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin; // 관리자 고유번호

    @Column(nullable = false)
    private Integer totalQuantity; // 하나의 상품의 총 개수

    @Column(nullable = false)
    private Integer totalPrice; // 하나의 상품의 총 가격

    private Double fee; //수수료
    private Double formattedFee; // 포맷팅된 수수료를 저장할 변수 추가

    @Builder
    public OrderHistory(Integer id, Order order, Items items, Admin admin, Integer totalQuantity, Integer totalPrice) {
        this.id = id;
        this.order = order;
        this.items = items;
        this.admin = admin;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }
}
