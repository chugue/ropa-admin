package com.example.finalproject.domain.orderHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    // 브랜드 별 사용자가 구매한 아이템 주문 목록 (관리자)
    public List<OrderHistoryResponse.orderList> findByOrderHistoryItemsAdmin(Integer adminId) {
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findByOrderHistoryItemsAdmin(adminId);
        return orderHistoryList.stream().map(OrderHistoryResponse.orderList::new).toList();
    }

    // 사용자 주문 목록 (사용자)
    public OrderHistoryResponse.UserOrderHistory getOrderHistoryByUserId(Integer userId) {
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findOrderHistoryByUserId(userId);
        List<OrderHistoryResponse.ItemHistory> itemHistoryDTOList = orderHistoryList.stream()
                .map(OrderHistoryResponse.ItemHistory::new).collect(Collectors.toList());
        return new OrderHistoryResponse.UserOrderHistory(userId, itemHistoryDTOList);
    }
}
