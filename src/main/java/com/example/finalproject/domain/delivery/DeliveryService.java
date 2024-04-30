package com.example.finalproject.domain.delivery;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import com.example.finalproject.domain.orderHistory.OrderHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryService {
    private final OrderHistoryRepository orderHistoryRepository;

    // 브랜드 별 사용자가 구매한 아이템 배송 목록
    public List<OrderHistoryResponse.DeliveryListDTO> findByOrderHistoryItemsAdminAndDelivery(Integer adminId) {
        List<OrderHistory> orderDeliveryList = orderHistoryRepository.findByOrderHistoryItemsAdminAndDelivery(adminId);
        return orderDeliveryList.stream()
                .map(orderHistory -> new OrderHistoryResponse.DeliveryListDTO(orderHistory, orderHistory.getOrder().getUser(),
                        orderHistory.getOrder().getDelivery().getDeliveryAddress(),
                        orderHistory.getOrder().getDelivery()))
                .toList();
    }
}
