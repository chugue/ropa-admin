package com.example.finalproject.domain.delivery;

import com.example.finalproject.domain.deliveryAddress.DeliveryAddress;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "delivery_tb")
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "delivery_address_id", nullable = false)
    private DeliveryAddress deliveryAddress; // 배송지 고유번호

    @Column(nullable = false)
    private String status; // 배송 현황

    @Column(nullable = false)
    private Timestamp startDate; // 배송시작일

    private Timestamp endDate; // 배송도착일

    @Builder
    public Delivery(Integer id, DeliveryAddress deliveryAddress, String status, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
