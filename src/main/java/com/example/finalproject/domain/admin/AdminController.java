package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DecimalFormat;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;
    private final HttpSession session;


    //로그인
    @PostMapping("/login")
    public String login(AdminRequest.LoginDTO reqDTO, HttpServletRequest req) {
        Admin sessionAdmin = adminService.login(reqDTO);
        session.setAttribute("sessionAdmin", sessionAdmin);
        req.setAttribute("Admin", sessionAdmin);
        return "index";
    }

    //회원가입 관리자/브랜드
    @PostMapping("/join")
    public String join(AdminRequest.JoinDTO reqDTO, HttpServletRequest req) {
        Admin admin = adminService.join(reqDTO);
        req.setAttribute("Admin", admin);
        return "/admin/join-form";
    }

    // 회원가입 폼
    @GetMapping("/loginForm")
    public String loginForm(){
        return "admin/login-form";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "/admin/join-form";
    }

    // 관리자 매출관리 페이지
    @GetMapping("/api/admin-sales-manage")
    public String adminSalesManage(HttpServletRequest req) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionAdmin");
        List<OrderHistory> orderHistoryList = adminService.adminOrderHistory(sessionAdmin.getId());

        // adminOrderHistory 메서드 내에서 DecimalFormat 객체 생성
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        // 매출 목록을 순회하면서 각 OrderHistory의 수수료를 포맷팅하여 설정
        for (OrderHistory orderHistory : orderHistoryList) {
            // 현재 수수료 값 가져오기
            double fee = orderHistory.getFee();
            // 수수료 포맷팅
            String formattedFeeString = decimalFormat.format(fee);
            // 포맷팅된 수수료를 double 형태로 변환하여 OrderHistory 객체에 설정
            double formattedFee = Double.parseDouble(formattedFeeString);
            orderHistory.setFormattedFee(formattedFee);

        }
        //총 매출 메서드 불러오기
        double totalSalesAmount = adminService.getTotalSalesAmount(orderHistoryList);

        //총 수수료 계산 메서드 불러오기
        double totalFee = adminService.getTotalFee(orderHistoryList);

        // 모델에 포맷팅된 매출 목록을 추가하여 머스테치 템플릿에서 참조할 수 있도록 함
        req.setAttribute("orderHistoryList", orderHistoryList);
        req.setAttribute("totalSalesAmount", totalSalesAmount);
        req.setAttribute("totalFee", totalFee);

        return "sales/admin-sales-manage";
    }

    // 브랜드 매출관리 페이지
    @GetMapping("/api/brand-sales-manage")
    public String brandSalesManage() {
        return "sales/brand-sales-manage";
    }

    // 회원 관리 페이지
    @GetMapping("/api/user-manage")
    public String userManage() {
        return "admin/user-manage";
    }

}
