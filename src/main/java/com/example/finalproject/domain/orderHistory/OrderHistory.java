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
@Table(name = "order_history_tb")
@Data
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "admin_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin; // 관리자 고유번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order; // 주문 고유번호

    @JoinColumn(name = "items_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Items items; // 아이템 고유번호

    @Column(nullable = false)
    private Integer orderItemQty; // 한 주문에서 한 상품의 총 판매개수

    @Column(nullable = false)
    private Integer orderItemPrice; // 한 주문에서 한 상품의 총 판매 가격

    private Double fee; //수수료

    private Integer formattedFee; // 포맷팅된 수수료를 저장할 변수 추가

    @Builder
    public OrderHistory(Integer id, Admin admin, Order order, Items items, Integer orderItemQty, Integer orderItemPrice, Double fee, Integer formattedFee) {
        this.id = id;
        this.admin = admin;
        this.order = order;
        this.items = items;
        this.orderItemQty = orderItemQty;
        this.orderItemPrice = orderItemPrice;
        this.fee = fee;
        this.formattedFee = formattedFee;
    }
}
