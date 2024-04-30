package com.example.finalproject.orderHistory;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class OrderHistoryRepositoryTest {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    // 브랜드의 매출관리
    @Test
    public void findByAdminId_test() {
        //given
        int adminId = 1;

        //when
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findByAdminIdWithItems(adminId);

        //then
        orderHistoryList.forEach(System.out::println);
    }

    @Test
    public void findAll_test() {
        //given

        //when
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findAll();

        //then
        orderHistoryList.forEach(System.out::println);
    }

    @Test
    public void findByOrderHistoryItemsAdmin_test() {
        //given
        int adminId = 1;

        //when
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findByOrderHistoryItemsAdmin(adminId);

        //then
        orderHistoryList.forEach(System.out::println);
    }
}
