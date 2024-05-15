package com.example.finalproject.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    //개인이 주문한 상품의 총 갯수
    @Query("SELECT CAST(SUM(oh.orderItemQty) AS INTEGER) FROM OrderHistory oh WHERE oh.order.user.id = :userId")
    Integer getTotalOrderItemQtyByUserId(@Param("userId") Long userId);


    // 오더 페이지에 필요한 정보 추출 배송정보랑 사용자 정보 포함
    @Query("select o from Order o join fetch o.user u join fetch o.delivery d where o.user.id = :userId order by d.endDate desc")
    List<Order> findByUserId(@Param("userId") Integer userId);


}