package com.example.finalproject.domain.order;

import com.example.finalproject.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.orderDetail.items.admin.id = ?1")
    List<Order> findByOrderDetailItemsAdmin(@Param("adminId") Integer adminId);
}