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
    public List<OrderHistoryResponse.orderListDTO> findByOrderHistoryItemsAdmin(Integer adminId) {
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findByOrderHistoryItemsAdmin(adminId);
        return orderHistoryList.stream().map(OrderHistoryResponse.orderListDTO::new).toList();
    }

    // 사용자 주문 목록 (사용자)
    public OrderHistoryResponse.UserOrderHistoryDTO getOrderHistoryByUserId(Integer userId) {
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findOrderHistoryByUserId(userId);
        List<OrderHistoryResponse.ItemHistoryDTO> itemHistoryDTOList = orderHistoryList.stream()
                .map(OrderHistoryResponse.ItemHistoryDTO::new).collect(Collectors.toList());
        return new OrderHistoryResponse.UserOrderHistoryDTO(userId, itemHistoryDTOList);
    }
}
