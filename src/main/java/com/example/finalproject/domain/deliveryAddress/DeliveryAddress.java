package com.example.finalproject.domain.deliveryAddress;


import com.example.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "delivery_address_tb")
@Data
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String recipient; // 수령인

    @Column(nullable = false)
    private String postalCode; // 우편번호

    @Column(nullable = false)
    private String address; // 주소

    private String addressDetail; // 상세주소

    private String phoneNumber; // 연락처

    @Builder
    public DeliveryAddress(Integer id, User user, String recipient, String postalCode, String address, String addressDetail, String phoneNumber) {
        this.id = id;
        this.user = user;
        this.recipient = recipient;
        this.postalCode = postalCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.phoneNumber = phoneNumber;
    }
}
