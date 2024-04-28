package com.example.finalproject.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.orderHistory.items.admin.id = :adminId")
    List<Order> findByOrderDetailItemsAdmin(@Param("adminId") Integer adminId);
}