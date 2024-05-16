package com.example.finalproject.domain.admin;

import com.example.finalproject._core.error.exception.Exception400;
import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception403;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject._core.utils.Formatter;
import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import com.example.finalproject.domain.photo.PhotoService;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final UserRepository userRepository;
    private final PhotoService photoService;

    //브랜드가 로그인 했을 때 매출 목록보기
    public AdminResponse.BrandSalesManagement brandOrderHistory(int adminId, LocalDateTime startDate, LocalDateTime endDate) {
        List<OrderHistory> brandOrderHistoryDTO;

        if (startDate == null || endDate == null) {
            brandOrderHistoryDTO = orderHistoryRepository.findByAdminIdWithItems(adminId);
        } else {
            Timestamp startTimestamp = Timestamp.valueOf(startDate);
            Timestamp endTimestamp = Timestamp.valueOf(endDate);
            brandOrderHistoryDTO = orderHistoryRepository.findByAdminIdWithItemsAndDate(adminId, startTimestamp, endTimestamp);
        }
        if (brandOrderHistoryDTO == null) {
            throw new Exception404("현재 주문 내역이 존재하지 않습니다.");
        }
        List<AdminResponse.BrandOrderHistoryList> brandOrderHistory = brandOrderHistoryDTO.stream()
                .map(AdminResponse.BrandOrderHistoryList::new).collect(Collectors.toList());
        int totalSalesAmount = orderHistoryRepository.findByAdminAndOrderItemPrice(adminId);
        int fee = (int) (totalSalesAmount * 0.1);

        return new AdminResponse.BrandSalesManagement(Formatter.number(totalSalesAmount), Formatter.number(fee), brandOrderHistory);
    }

    //관리자가 로그인 했을 때 주문 목록 보기
    public List<OrderHistory> adminOrderHistory(Integer adminId) {

        List<OrderHistory> adminOrderHistoryList = orderHistoryRepository.findAll();

        if (adminOrderHistoryList == null) {
            throw new Exception404("현재 주문내역이 존재 하지 않습니다.");
        }
        return adminOrderHistoryList;
    }

    //관리자가 로그인했을 때 매출 목록보기
    public AdminResponse.AdminSalesManagement adminSalesListDTOList(String searchBy, String keyword) {

        List<AdminResponse.SalesList> salesList = switch (searchBy) {
            case "adminId" -> orderHistoryRepository.getTotalSalesAndFeePerBrandAndBrandId(keyword);
            case "brandName" -> orderHistoryRepository.getTotalSalesAndFeePerBrandAndBrandName(keyword);
            case null, default -> orderHistoryRepository.getTotalSalesAndFeePerBrand();
        };

        int totalSalesAmount = orderHistoryRepository.getTotalOrderItemPrice();
        int totalFee = (int) (totalSalesAmount * 0.1);
        return new AdminResponse.AdminSalesManagement(Formatter.number(totalSalesAmount), Formatter.number(totalFee), salesList);
    }


    //로그인
    public Admin login(AdminRequest.LoginDTO reqDTO) {
        Admin admin = adminRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증 되지 않았습니다."));
        return admin;
    }

    //회원가입
    @Transactional
    public Admin join(AdminRequest.JoinDTO reqDTO) {
        Optional<Admin> adminOP = adminRepository.findByEmail(reqDTO.getEmail());
        if (adminOP.isPresent()) {
            throw new Exception400("중복된 이메일이 있습니다.");
        }

        Admin admin = adminRepository.save(reqDTO.toBrandEntity());

        photoService.uploadBrandImage(reqDTO.getBrandImage(), admin);
        return admin;
    }

    // 크리에이터 관리 페이지
    public List<AdminResponse.CreatorList> creatorList(String searchBy, String keyword) {
        List<User> userList = switch (searchBy) {
            case "myName" -> userRepository.findByMyName(keyword);
            case "nickName" -> userRepository.findByNickName(keyword);
            case "email" -> userRepository.findByEmail(keyword);
            case null, default -> userRepository.findAll();
        };

        // Stream API를 사용하여 필터링 및 매핑을 진행합니다.
        return userList.stream()
                .filter(user -> "승인 대기".equals(user.getStatus()) || "승인".equals(user.getStatus()))
                .sorted((u1, u2) -> {
                    if (u1.getApplyTime() == null && u2.getApplyTime() != null) return 1;
                    if (u1.getApplyTime() != null && u2.getApplyTime() == null) return -1;
                    if (u1.getApplyTime() == null && u2.getApplyTime() == null) return 0;
                    return u2.getApplyTime().compareTo(u1.getApplyTime()); // 최신 순으로 정렬
                })
                .map(AdminResponse.CreatorList::new)
                .collect(Collectors.toList());

    }


    // 유저 관리
    public List<AdminResponse.UserList> getUserList(String searchBy, String keyword) {
        List<User> userList = switch (searchBy) {
            case "myName" -> userRepository.findByMyName(keyword);
            case "nickName" -> userRepository.findByNickName(keyword);
            case "email" -> userRepository.findByEmail(keyword);
            case null, default -> userRepository.findAll();
        };
        return userList.stream().map(AdminResponse.UserList::new).toList();
    }


    // 유저 크리에이터 신청 승인
    public void approveCreatorStatus(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception400("잘못된 사용자 아이디입니다."));
        if (Objects.equals(user.getStatus(), "신청 전")) {
            throw new Exception403("사용자가 크리에이터 신청한 이력이 없습니다.");
        }
        user.setStatus("승인");
        user.setBlueChecked(true);
        userRepository.save(user);
    }

    // 유저 크리에이터 신청 거절
    public void rejectCreatorStatus(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception400("잘못된 사용자 아이디입니다."));
        if (Objects.equals(user.getStatus(), "신청 전")) {
            throw new Exception403("사용자가 크리에이터 신청한 이력이 없습니다.");
        }
        user.setStatus("거절");
        userRepository.save(user);
    }


}
