package com.example.finalproject.domain.orderHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
    //브랜드의 매출 목록
    @Query("SELECT oh FROM OrderHistory oh JOIN FETCH oh.items WHERE oh.admin.id = :adminId")
    List<OrderHistory> findByAdminIdWithItems(@Param("adminId") int adminId);

    //관리자의 매출 목록
    @Query("select oh from OrderHistory oh join FETCH oh.order where oh.admin.id = :adminId")
    List<OrderHistory> findOrderHistoryByAdminIdWithOrder(@Param("adminId") int adminId);
}
