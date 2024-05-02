package com.example.finalproject.domain.admin;

import com.example.finalproject._core.error.exception.Exception400;
import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception403;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject._core.utils.JwtUtill;
import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.finalproject.domain.admin.Admin.AdminRole.ADMIN;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final UserRepository userRepository;

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


    //관리자가 로그인 했을 때 주문 목록 보기
    public List<OrderHistory> adminOrderHistory(Integer adminId) {

        List<OrderHistory> adminOrderHistoryList = orderHistoryRepository.findAll();

        if (adminOrderHistoryList == null) {
            throw new Exception404("현재 주문내역이 존재 하지 않습니다.");
        }
        return adminOrderHistoryList;
    }

    //관리자가 로그인했을 때 매출 목록보기
    public List<AdminResponse.SalesListDTO> adminSalesListDTOList() {

        List<AdminResponse.SalesListDTO> respDTO = orderHistoryRepository.getTotalSalesAndFeePerBrand();

        return respDTO;
    }


    //로그인
    public String login(AdminRequest.LoginDTO reqDTO) {
        Admin admin = adminRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증 되지 않았습니다."));
        String jwt  = JwtUtill.create(admin);
        return jwt;
    }

    //회원가입
    @Transactional
    public Admin join(AdminRequest.JoinDTO reqDTO) {
        Optional<Admin> adminOP = adminRepository.findByEmail(reqDTO.getEmail());
        if (adminOP.isPresent()) {
            throw new Exception400("중복된 이메일이 있습니다.");
        }

        Admin admin = null;

        if (reqDTO.getRole().equals(ADMIN)) {
            admin = adminRepository.save(reqDTO.toAdminEntity());
        } else if (reqDTO.getRole().equals(Admin.AdminRole.BRAND)) {
            admin = adminRepository.save(reqDTO.toBrandEntity());
        }
        return admin;
    }

    // 유저 크리에이터 인증 관리
    public List<AdminResponse.UserListDTO> getUserList() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(AdminResponse.UserListDTO::new).toList();
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
