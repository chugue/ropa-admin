package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.admin.AdminResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {

    //관리자의 브랜드별 매출 목록보기
    @Query("SELECT NEW com.example.finalproject.domain.admin.AdminResponse$SalesList(oh.admin ,SUM(oh.orderItemPrice), SUM(oh.fee)) " +
            "FROM OrderHistory oh " +
            "GROUP BY oh.admin.id")
    List<AdminResponse.SalesList> getTotalSalesAndFeePerBrand();

    //관리자의 브랜드별 매출 브랜드명 검색 목록보기
    @Query("SELECT NEW com.example.finalproject.domain.admin.AdminResponse$SalesList(oh.admin ,SUM(oh.orderItemPrice), SUM(oh.fee)) " +
            "FROM OrderHistory oh " +
            "where oh.admin.brandName like %:keyword% " +
            "GROUP BY oh.admin.id")
    List<AdminResponse.SalesList> getTotalSalesAndFeePerBrandAndBrandName(@Param("keyword") String keyword);

    //관리자의 브랜드별 매출 브랜드코드 검색 목록보기
    @Query("SELECT NEW com.example.finalproject.domain.admin.AdminResponse$SalesList(oh.admin ,SUM(oh.orderItemPrice), SUM(oh.fee)) " +
            "FROM OrderHistory oh " +
            "where cast(oh.admin.id as string) like %:keyword% " +
            "GROUP BY oh.admin.id")
    List<AdminResponse.SalesList> getTotalSalesAndFeePerBrandAndBrandId(@Param("keyword") String keyword);

    //관리자의 매출 목록
    @Query("select oh from OrderHistory oh join FETCH oh.order where oh.admin.id = :adminId")
    List<OrderHistory> findOrderHistoryByAdminIdWithOrder(@Param("adminId") int adminId);

    //브랜드의 매출 목록
    @Query("SELECT oh FROM OrderHistory oh JOIN FETCH oh.items WHERE oh.admin.id = :adminId")
    List<OrderHistory> findByAdminIdWithItems(@Param("adminId") int adminId);

    //브랜드의 매출 기간 검색 목록
    @Query("SELECT oh FROM OrderHistory oh JOIN FETCH oh.items i join fetch oh.order o WHERE oh.admin.id = :adminId and o.orderDate BETWEEN :startDate AND :endDate")
    List<OrderHistory> findByAdminIdWithItemsAndDate(@Param("adminId") int adminId, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    // 아이템 주문 목록 중 로그인한 브랜드 관리자가 등록한 아이템 찾기
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.user u join fetch oh.items i where i.admin.id = :adminId")
    List<OrderHistory> findByOrderHistoryItemsAdmin(@Param("adminId") Integer adminId);

    // 아이템 주문 목록 중 로그인한 브랜드 관리자가 등록한 아이템 고객명 검색
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.user u join fetch oh.items i where i.admin.id = :adminId AND u.myName like %:keyword%")
    List<OrderHistory> findByOrderHistoryItemsAdminAndUsername(@Param("adminId") Integer adminId, @Param("keyword") String keyword);

    // 아이템 주문 목록 중 로그인한 브랜드 관리자가 등록한 아이템 연락처 검색
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.user u join fetch oh.items i where i.admin.id = :adminId AND u.mobile like %:keyword%")
    List<OrderHistory> findByOrderHistoryItemsAdminAndMobile(@Param("adminId") Integer adminId, @Param("keyword") String keyword);

    // 아이템 주문 목록 중 로그인한 브랜드 관리자가 등록한 아이템 코드 검색
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.user u join fetch oh.items i where i.admin.id = :adminId AND cast(i.id as string) like %:keyword%")
    List<OrderHistory> findByOrderHistoryItemsAdminAndItemId(@Param("adminId") Integer adminId, @Param("keyword") String keyword);

    // 아이템 주문 목록 중 로그인한 브랜드 관리자가 등록한 아이템명 검색
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.user u join fetch oh.items i where i.admin.id = :adminId AND i.name like %:keyword%")
    List<OrderHistory> findByOrderHistoryItemsAdminAndItemName(@Param("adminId") Integer adminId, @Param("keyword") String keyword);

    // 로그인한 브랜드 관리자 별 사용자가 구매한 아이템 배송 목록
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.delivery d join fetch o.user u join fetch oh.items i where i.admin.id = :adminId")
    List<OrderHistory> findByOrderHistoryItemsAdminAndDelivery(@Param("adminId") Integer adminId);

    // 로그인한 브랜드 관리자 별 사용자가 구매한 아이템 배송 구매자 검색 목록
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.delivery d join fetch o.user u join fetch oh.items i where i.admin.id = :adminId and cast(o.id as string) like %:keyword%")
    List<OrderHistory> findByOrderHistoryItemsAdminAndDeliveryAndOrderId(@Param("adminId") Integer adminId, @Param("keyword") String keyword);

    // 로그인한 브랜드 관리자 별 사용자가 구매한 아이템 배송 구매자 검색 목록
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.delivery d join fetch o.user u join fetch oh.items i where i.admin.id = :adminId and u.myName like %:keyword%")
    List<OrderHistory> findByOrderHistoryItemsAdminAndDeliveryAndUsername(@Param("adminId") Integer adminId, @Param("keyword") String keyword);

    // 로그인한 브랜드 관리자 별 사용자가 구매한 아이템 배송 수령인 검색 목록
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.delivery d join fetch o.user u join fetch oh.items i where i.admin.id = :adminId and d.recipient like %:keyword%")
    List<OrderHistory> findByOrderHistoryItemsAdminAndDeliveryAndRecipient(@Param("adminId") Integer adminId, @Param("keyword") String keyword);

    // 로그인한 브랜드 관리자 별 사용자가 구매한 아이템 배송 수령인 연락처 검색 목록
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.delivery d join fetch o.user u join fetch oh.items i where i.admin.id = :adminId and d.phoneNumber like %:keyword%")
    List<OrderHistory> findByOrderHistoryItemsAdminAndDeliveryAndRecipientPhoneNumber(@Param("adminId") Integer adminId, @Param("keyword") String keyword);

    // 로그인한 브랜드 관리자 별 사용자가 구매한 아이템 배송 현황 검색 목록
    @Query("select oh from OrderHistory oh join fetch oh.order o join fetch o.delivery d join fetch o.user u join fetch oh.items i where i.admin.id = :adminId and d.status like %:keyword%")
    List<OrderHistory> findByOrderHistoryItemsAdminAndDeliveryAndStatus(@Param("adminId") Integer adminId, @Param("keyword") String keyword);

    // 각 아이템의 총 판매수량대로 정렬하여서 각 아이템의 id를 나열
    @Query("SELECT oh.items.id FROM OrderHistory oh GROUP BY oh.items.id ORDER BY SUM(oh.orderItemQty) DESC")
    List<Integer> findItemsIdByTotalSales();

    // 주문 목록에 있는 아이템과 배송현황 찾기
    @Query("select oh from OrderHistory oh join fetch oh.items i join fetch i.photos p join fetch i.category c join fetch oh.order o join fetch o.delivery d join fetch o.user u where u.id = :userId")
    List<OrderHistory> findOrderHistoryByUserId(@Param("userId") Integer userId);
}
