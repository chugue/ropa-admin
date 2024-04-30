package com.example.finalproject.domain.orderHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
    //브랜드의 총 매출 내역
    @Query("SELECT SUM(oh.totalPrice) FROM OrderHistory oh WHERE oh.admin.id = :adminId")
    Double getTotalSalesForBrand(@Param("adminId") int adminId);

    //브랜드의 총 수수료 내역
    @Query("SELECT SUM(oh.fee) FROM OrderHistory oh WHERE oh.admin.id = :adminId")
    Double getTotalFeeForBrand(@Param("adminId") int adminId);

    //관리자의 매출 목록
    @Query("select oh from OrderHistory oh join FETCH oh.order where oh.admin.id = :adminId")
    List<OrderHistory> findOrderHistoryByAdminIdWithOrder(@Param("adminId") int adminId);

    //브랜드의 매출 목록
    @Query("SELECT oh FROM OrderHistory oh JOIN FETCH oh.items WHERE oh.admin.id = :adminId")
    List<OrderHistory> findByAdminIdWithItems(@Param("adminId") int adminId);

    // 아이템 주문 목록 중 로그인한 브랜드 관리자가 등록한 아이템 찾기
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.user u join fetch oh.items i where i.admin.id = :adminId")
    List<OrderHistory> findByOrderHistoryItemsAdmin(@Param("adminId") int adminId);
}
