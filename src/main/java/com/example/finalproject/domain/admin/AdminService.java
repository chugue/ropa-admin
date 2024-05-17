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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final UserRepository userRepository;
    private final PhotoService photoService;

    //브랜드가 로그인 했을 때 매출 목록보기
    public AdminResponse.BrandSalesManagement brandOrderHistory(int adminId) {
        // 해당 adminId로 주문 내역을 조회
        List<OrderHistory> brandOrderHistoryDTO = orderHistoryRepository.findByAdminIdWithItems(adminId);

        // 주문 내역이 존재하지 않으면 404 예외를 발생
        if (brandOrderHistoryDTO == null || brandOrderHistoryDTO.isEmpty()) {
            throw new Exception404("현재 주문 내역이 존재하지 않습니다.");
        }

        // 아이템 ID를 키로 하여 주문 내역을 저장할 맵을 생성
        Map<Integer, AdminResponse.BrandOrderHistoryList> orderHistoryMap = new HashMap<>();

        // 조회된 주문 내역을 반복 처리
        for (OrderHistory orderHistory : brandOrderHistoryDTO) {
            // 각 주문 내역의 아이템 ID를 찾기
            int itemId = orderHistory.getItems().getId();
            // 아이템 ID를 키로 하여 기존의 주문 내역을 맵에서 조회
            AdminResponse.BrandOrderHistoryList existing = orderHistoryMap.get(itemId);

            // 맵에 기존에 없는 아이템이면 새로 추가
            if (existing == null) {
                // 새로운 주문 내역 리스트 항목을 생성하고 맵에 추가
                AdminResponse.BrandOrderHistoryList newEntry = new AdminResponse.BrandOrderHistoryList(orderHistory);
                orderHistoryMap.put(itemId, newEntry);
            } else {
                // 기존에 있는 아이템이면 가격과 수량을 합산하여 업데이트
                // 기존 총 가격을 숫자로 변환
                int currentTotalPrice = Formatter.parseNumber(existing.getTotalPrice());
                // 새로운 총 가격을 계산
                int newTotalPrice = currentTotalPrice + orderHistory.getOrderItemPrice();
                // 계산된 총 가격을 포맷하여 설정
                existing.setTotalPrice(Formatter.number(newTotalPrice));
                // 총 수량을 합산하여 설정
                existing.setTotalQuantity(existing.getTotalQuantity() + orderHistory.getOrderItemQty());
            }
        }

        // 맵에 저장된 주문 내역 리스트를 생성
        List<AdminResponse.BrandOrderHistoryList> brandOrderHistory = new ArrayList<>(orderHistoryMap.values());
        // 총 매출 금액을 계산
        int totalSalesAmount = brandOrderHistory.stream().mapToInt(item -> Formatter.parseNumber(item.getTotalPrice())).sum();
        // 수수료를 계산 (총 매출 금액의 10%)
        int fee = (int) (totalSalesAmount * 0.1);

        // 최종 결과를 AdminResponse.BrandSalesManagement 객체로 반환
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
                .orElseThrow(() -> new Exception401("존재 하지 않는 계정입니다."));
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

    // 회원정보 수정
    @Transactional
    public void update(AdminRequest.UpdateDTO reqDTO, Integer userId) {
        Admin admin = adminRepository.findById(userId)
                .orElseThrow(() -> new Exception404("사용자 아이디를 찾을 수 없습니다."));

        admin.setEmail(reqDTO.getEmail());
        admin.setPassword(reqDTO.getPassword());
        admin.setBrandName(reqDTO.getBrandName());
        admin.setPhone(reqDTO.getPhone());
        admin.setAddress(reqDTO.getAddress());
        admin.setBusinessNum(reqDTO.getBusinessNum());

        Admin savedAdmin = adminRepository.save(admin);
        photoService.updateBrandImage(reqDTO.getBrandImage(), savedAdmin);

    }

    // 회원 정보 확인
    public AdminResponse.UserInfo getUserInfo(Integer userId) {
        Admin admin = adminRepository.findByIdWithPhoto(userId)
                .orElseThrow(() -> new Exception404("사용자 아이디를 찾을 수 없습니다."));
        return new AdminResponse.UserInfo(admin);
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
