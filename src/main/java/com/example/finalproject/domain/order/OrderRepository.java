package com.example.finalproject.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    // 주문상세 내역에서 해당 브랜드가 등록한 아이템들 조회
    @Query("select o from Order o join fetch o.orderHistory h join fetch h.items i where i.admin.id = :adminId")
    List<Order> findByOrderHistoryItemsAdmin(@Param("adminId") Integer adminId);
}