package com.example.finalproject.orderHistory;

import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrderHistoryRepositoryTest {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;


    @Test
    public void creator_test(){
        // given
        Integer id = 3;

        // when
        Integer totalQty = orderHistoryRepository.getTotalOrderItemQtyByUserId(Long.valueOf(id));


        // then
        System.out.println("totalQty : "+totalQty);

    }
}
