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
    private User user; // 사용자 고유번호

    @OneToOne
    private Delivery delivery; // 배송 고유번호

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;  // 배송타입 (기본:무료/로켓:3000원)

    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;  // 결제 타입 (카카오 / 토스 / 신용카드 / 계좌이체 )

    private Integer purchaseAmount; // 주문 최종 결제 금액

    private Double fee; // 주문 전체 수수료 매출액의 5퍼를 로파관리자 5퍼는 크리에이터

    @CreationTimestamp
    private Timestamp orderDate; // 주문일자

    @Builder
    public Order(Integer id, User user, Delivery delivery, DeliveryType deliveryType, PayMethod payMethod, Integer purchaseAmount, Double feeToRopaCreator, Timestamp orderDate) {
        this.id = id;
        this.user = user;
        this.delivery = delivery;
        this.deliveryType = deliveryType;
        this.payMethod = payMethod;
        this.purchaseAmount = purchaseAmount;
        this.fee = feeToRopaCreator;
        this.orderDate = orderDate;
    }

    private enum DeliveryType {
        FREE, ROCKET
    }

    private enum PayMethod {
        TOSS, KAKAO, CREDIT, TRANSFER
    }
}