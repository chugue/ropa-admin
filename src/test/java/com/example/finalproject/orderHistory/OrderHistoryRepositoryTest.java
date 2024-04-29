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

    //브랜드의 매출관리
    @Test
    public void findByAdminIdTest() {
        //given
       int adminId = 1;

        //when
        List<OrderHistory>  orderHistoryList = orderHistoryRepository.findByAdminId(adminId);

        //then
        orderHistoryList.forEach(System.out::println);
    }

    @Test
    public void findAll() {
        //given

        //when
        List<OrderHistory>  orderHistoryList = orderHistoryRepository.findAll();

        //then
        orderHistoryList.forEach(System.out::println);
    }
}
