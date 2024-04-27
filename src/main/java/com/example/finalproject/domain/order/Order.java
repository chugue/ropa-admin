package com.example.finalproject.domain.order;

import com.example.finalproject.domain.delivery.Delivery;
import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "order_tb")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 사용자 고유번호

    @OneToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery; // 배송 고유번호

    @Column(nullable = false)
    private Integer totalOrderAmount; // 주문 전체 금액

    private Double fee; // 주무 전체 수수료

    @CreationTimestamp
    private Timestamp orderDate; // 주문일자



    @Builder
    public Order(Integer id, User user, Delivery delivery, Integer totalOrderAmount, Timestamp orderDate) {
        this.id = id;
        this.user = user;
        this.delivery = delivery;
        this.totalOrderAmount = totalOrderAmount;
        this.orderDate = orderDate;
    }
}
