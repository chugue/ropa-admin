package com.example.finalproject.domain.orderHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
    @Query("select o from OrderHistory o where o.admin.id = ?1")
    List<OrderHistory> findByAdminId(@Param("adminId") Integer id);


}
