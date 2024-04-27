package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;


    //브랜드 별 매출 목록보기
    private List<OrderHistory> orderHistoryList(Integer adminId){

        //1. 브랜드 별  매출 가져오기
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findByAdminId(adminId);

        //2. DTO에 담기
        List<>



        //2.
    }
}
