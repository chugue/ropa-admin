package com.example.finalproject.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    // 해당 브랜드로 주문된 아이템 주문 목록 조회
    public List<OrderResponse.orderListDTO> findByOrderHistoryItemsAdmin(Integer adminId) {
        System.out.println("1111111");
        List<Order> orderList = orderRepository.findByOrderHistoryItemsAdmin(adminId);
        System.out.println("22222222");
        return orderList.stream().map(order -> new OrderResponse.orderListDTO (order, order.getOrderHistory().getItems())).toList();

    }
}
