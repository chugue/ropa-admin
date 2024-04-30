package com.example.finalproject.items;

import com.example.finalproject.domain.items.ItemsRepository;
import com.example.finalproject.domain.orderHistory.OrderHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ItemsRepositoryTest {
    @Autowired
    private  ItemsRepository itemsRepository;

    @Test
    public void findAllWithInfo_test(){
        // given
        Integer adminId = 1;
        // when
        List<OrderHistory> respList = itemsRepository.findAllWithInfo(adminId);
        // then
        respList.forEach(System.out::println);
    }
}
