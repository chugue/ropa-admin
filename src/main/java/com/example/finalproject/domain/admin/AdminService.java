package com.example.finalproject.domain.admin;

import com.example.finalproject._core.error.exception.Exception400;
import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    //관리자가 로그인 했을 때 매출 목록 보기
    public List<OrderHistory> adminOrderHistory(Integer adminId){

        List<OrderHistory> adminOrderHistoryList = orderHistoryRepository.findAll();

        if(adminOrderHistoryList == null){
            throw  new Exception404("현재 주문내역이 존재 하지 않습니다.");
        }



        return adminOrderHistoryList;
    }

    //총 매출 계산
    public double getTotalSalesAmount(List<OrderHistory> orderHistoryList) {
        double totalSalesAmount = 0.0;
        for (OrderHistory orderHistory : orderHistoryList) {
            totalSalesAmount += orderHistory.getTotalPrice();
        }
        return totalSalesAmount;
    }


    //로그인
    @Transactional
    public Admin login(AdminRequest.LoginDTO reqDTO){
            Admin admin = adminRepository.findByEmailAndPassword(reqDTO.getEmail(),reqDTO.getPassword())
                    .orElseThrow(() -> new Exception401("인증 되지 않았습니다."));
            return admin;
    }

    //회원가입
    @Transactional
    public Admin join(AdminRequest.JoinDTO reqDTO){
        Optional<Admin> adminOP = adminRepository.findByEmail(reqDTO.getEmail());
        if (adminOP.isPresent()){
            throw new Exception400("중복된 이메일이 있습니다.");
        }

        Admin admin = null;


        if(reqDTO.getRole().equals(Admin.AdminRole.ADMIN)){
            admin = adminRepository.save(reqDTO.toAdminEntity());
        } else if (reqDTO.getRole().equals(Admin.AdminRole.BRAND)) {
            admin = adminRepository.save(reqDTO.toBrandEntity());
        }



        return admin;
    }
}
