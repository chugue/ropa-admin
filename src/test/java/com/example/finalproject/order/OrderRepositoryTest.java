package com.example.finalproject.order;

import com.example.finalproject.domain.order.Order;
import com.example.finalproject.domain.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findByOrderDetailItemsAdmin_test() {

        List<Order> orderList = orderRepository.findByOrderDetailItemsAdmin(1);

        orderList.forEach(System.out::println);
    }
}
