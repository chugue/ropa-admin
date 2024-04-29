package com.example.finalproject.domain.orderHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
    //브랜드의 매출 목록
    @Query("SELECT oh " +
            "FROM OrderHistory oh " +
            "WHERE oh.admin.brandName = :brandName")
    List<OrderHistory> findItemsInfoByBrandName(@Param("brandName") String brandName);

}
