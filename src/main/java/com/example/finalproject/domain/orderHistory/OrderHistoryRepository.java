package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.admin.AdminResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {

    //관리자의 브랜드별 매출 목록보기
    @Query("SELECT NEW com.example.finalproject.domain.admin.AdminResponse$SalesList(oh.admin ,SUM(oh.orderItemPrice), SUM(oh.fee)) " +
            "FROM OrderHistory oh " +
            "GROUP BY oh.admin.id")
    List<AdminResponse.SalesList> getTotalSalesAndFeePerBrand();

    //관리자의 매출 목록
    @Query("select oh from OrderHistory oh join FETCH oh.order where oh.admin.id = :adminId")
    List<OrderHistory> findOrderHistoryByAdminIdWithOrder(@Param("adminId") int adminId);

    //브랜드의 매출 목록
    @Query("SELECT oh FROM OrderHistory oh JOIN FETCH oh.items WHERE oh.admin.id = :adminId")
    List<OrderHistory> findByAdminIdWithItems(@Param("adminId") int adminId);

    // 아이템 주문 목록 중 로그인한 브랜드 관리자가 등록한 아이템 찾기
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.user u join fetch oh.items i where i.admin.id = :adminId")
    List<OrderHistory> findByOrderHistoryItemsAdmin(@Param("adminId") Integer adminId);

    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.delivery d join fetch o.user u join fetch oh.items i where i.admin.id = :adminId")
    List<OrderHistory> findByOrderHistoryItemsAdminAndDelivery(@Param("adminId") Integer adminId);

    // 각 아이템의 총 판매수량대로 정렬하여서 각 아이템의 id를 나열
    @Query("SELECT oh.items.id FROM OrderHistory oh GROUP BY oh.items.id ORDER BY SUM(oh.orderItemQty) DESC")
    List<Integer> findItemsIdByTotalSales();

    // 주문 목록에 있는 아이템과 배송현황 찾기
    @Query("select oh from OrderHistory oh join fetch oh.items i join fetch i.photos p join fetch i.category c join fetch oh.order o join fetch o.delivery d join fetch o.user u where u.id = :userId")
    List<OrderHistory> findOrderHistoryByUserId(@Param("userId") Integer userId);
}
