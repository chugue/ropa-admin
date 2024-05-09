package com.example.finalproject.domain.delivery;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "delivery_tb")
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String recipient; // 수령인

    private String postalCode; // 우편번호

    private String address; // 주소

    private String addressDetail; // 상세주소

    private String phoneNumber; // 연락처

    private String deliveryRequest;  // 배송 요청 사항

    @Column(nullable = false)
    private String status; // 배송 현황

    private Boolean isBaseAddress; // 기본 배송지 저장 여부

    @CreationTimestamp
    private Timestamp startDate; // 배송시작일

    @UpdateTimestamp
    private Timestamp endDate; // 배송도착일

    @Builder
    public Delivery(Integer id, String recipient, String postalCode, String address, String addressDetail, String phoneNumber, String deliveryRequest, String status, Boolean isBaseAddress, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.recipient = recipient;
        this.postalCode = postalCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.phoneNumber = phoneNumber;
        this.deliveryRequest = deliveryRequest;
        this.status = status;
        this.isBaseAddress = isBaseAddress;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
