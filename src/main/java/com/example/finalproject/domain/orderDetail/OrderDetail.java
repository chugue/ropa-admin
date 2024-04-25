package com.example.finalproject.domain.orderDetail;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.order.Order;
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

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 주문 고유번호

    @ManyToOne
    @JoinColumn(name = "order_items_id", nullable = false)
    private Items orderItems; // 아이템 고유번호

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin; // 관리자 고유번호

    @Column(nullable = false)
    private Integer totalQuantity; // 상품의 총 개수

    @Column(nullable = false)
    private Integer totalPrice; // 상품의 총 가격

    @Builder
    public OrderDetail(Integer id, Order order, Items orderItems, Admin admin, Integer totalQuantity, Integer totalPrice) {
        this.id = id;
        this.order = order;
        this.orderItems = orderItems;
        this.admin = admin;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }
}
