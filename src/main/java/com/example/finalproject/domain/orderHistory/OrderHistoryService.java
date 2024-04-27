package com.example.finalproject.domain.orderHistory;

import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    //관리자 뷰 매출 목록 보기
    private List<OrderHistory> adminOrderHistoryList(){

      List<OrderHistory> adminOrderHistoryList = orderHistoryRepository.findAll();

      if(adminOrderHistoryList == null){
          throw  new Exception404("현재 주문내역이 존재 하지 않습니다.");

      }

      return adminOrderHistoryList;
    }



    //브랜드 별 매출 목록보기
//    private List<OrderHistory> orderHistoryList(Integer adminId){
//
//        //1. 브랜드 별  매출 가져오기
//        List<OrderHistory> orderHistoryList = orderHistoryRepository.findByAdminId(adminId);
//
//        //2. DTO에 담기
//        List<>
//
//
//
//        //2.
//    }
}
