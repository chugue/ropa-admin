package com.example.finalproject.domain.orderHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
    //브랜드의 매출 목록

    @Query("SELECT oh FROM OrderHistory oh JOIN FETCH oh.items WHERE oh.admin.id = :adminId")
    List<OrderHistory> findByAdminIdWithItems(@Param("adminId") int adminId);

    // 아이템 주문 목록 중 로그인한 브랜드 관리자가 등록한 아이템 찾기
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.user u join fetch oh.items i where i.admin.id = :adminId")
    List<OrderHistory> findByOrderHistoryItemsAdmin(@Param("adminId") Integer adminId);

    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.delivery d join fetch d.deliveryAddress da join fetch o.user u join fetch oh.items i where i.admin.id = :adminId")
    List<OrderHistory> findByOrderHistoryItemsAdminAndDelivery(@Param("adminId") Integer adminId);
}
