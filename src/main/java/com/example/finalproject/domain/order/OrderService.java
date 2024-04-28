package com.example.finalproject.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    // 해당 브랜드 주문 목록 조회
    public List<OrderResponse.orderListDTO> findByOrderDetailItemsAdmin(Integer adminId) {
        List<Order> orderList = orderRepository.findByOrderDetailItemsAdmin(adminId);
        return orderList.stream().map(OrderResponse.orderListDTO::new).toList();
    }
}
