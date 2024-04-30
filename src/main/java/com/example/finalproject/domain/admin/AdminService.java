package com.example.finalproject.domain.admin;

import com.example.finalproject._core.error.exception.Exception400;
import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import com.example.finalproject.domain.orderHistory.OrderHistoryResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.finalproject.domain.admin.Admin.AdminRole.ADMIN;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final OrderHistoryRepository orderHistoryRepository;


    //브랜드가 로그인 했을 때 매출 목록보기
    public List<AdminResponse.BrandOrderHistoryListDTO> brandOrderHistory(int adminId) {
        List<OrderHistory> brandOrderHistory = orderHistoryRepository.findByAdminIdWithItems(adminId);

        if (brandOrderHistory == null) {
            throw new Exception404("현재 주문 내역이 존재하지 않습니다.");
        }

        List<AdminResponse.BrandOrderHistoryListDTO> respDTO = brandOrderHistory.stream().map(orderHistory -> {
            return new AdminResponse.BrandOrderHistoryListDTO(orderHistory);
        }).collect(Collectors.toList());

        return respDTO;
    }

    // 브랜드의 총 매출과 총 수수료를 가져오는 메소드
    public AdminResponse.AdminSalesListDTO getBrandSalesAndFee(int adminId) {
        // 브랜드의 총 매출과 총 수수료를 가져옵니다.
        Double totalSales = orderHistoryRepository.getTotalSalesForBrand(adminId);
        Double totalFee = orderHistoryRepository.getTotalFeeForBrand(adminId);

        // 총 매출과 총 수수료를 DTO로 변환하여 반환합니다.
        return new AdminResponse.AdminSalesListDTO(totalSales, totalFee);
    }

    // 관리자가 로그인 했을 때 매출 목록 보기
    public List<AdminResponse.AdminSalesListDTO> adminOrderHistory(int adminId) {
        // 브랜드의 총 매출과 총 수수료를 가져옵니다.
        AdminResponse.AdminSalesListDTO brandSalesAndFee = getBrandSalesAndFee(adminId);

        // 매출 목록을 반환합니다.
        List<AdminResponse.AdminSalesListDTO> salesList = new ArrayList<>();
        salesList.add(brandSalesAndFee);

        return salesList;
    }





//    //총 매출 계산
//    public double getTotalSalesAmount(List<AdminResponse.AdminSalesListDTO> orderHistoryList) {
//        double totalSalesAmount = 0.0;
//        for (AdminResponse.AdminSalesListDTO orderHistory : orderHistoryList) {
//            totalSalesAmount += orderHistory.getTotalPrice();
//        }
//        return totalSalesAmount;
//    }

//    // 총 수수료를 계산
//    public double getTotalFee(List<AdminResponse.AdminSalesListDTO> orderHistoryList) {
//        double totalFee = 0.0;
//        for (AdminResponse.AdminSalesListDTO orderHistory : orderHistoryList) {
//            totalFee += orderHistory.getFee();
//        }
//        return totalFee;
//    }


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

        if(reqDTO.getRole().equals(ADMIN)){
            admin = adminRepository.save(reqDTO.toAdminEntity());
        } else if (reqDTO.getRole().equals(Admin.AdminRole.BRAND)) {
            admin = adminRepository.save(reqDTO.toBrandEntity());
        }
        return admin;
    }
}
