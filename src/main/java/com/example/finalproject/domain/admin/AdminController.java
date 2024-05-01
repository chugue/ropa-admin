package com.example.finalproject.domain.admin;

import com.example.finalproject._core.error.exception.Exception403;
import com.example.finalproject.domain.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.example.finalproject.domain.admin.Admin.AdminRole.ADMIN;
import static com.example.finalproject.domain.admin.Admin.AdminRole.BRAND;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;
    private final HttpSession session;


    //로그인
    @PostMapping("/login")
    public String login(AdminRequest.LoginDTO reqDTO) {
        Admin admin = adminService.login(reqDTO);
        if(admin.getRole().equals(ADMIN)){
            session.setAttribute("sessionAdmin", admin);
            return "index-admin";
        } else if (admin.getRole().equals(BRAND)) {
            session.setAttribute("sessionBrand", admin);
            return "index-brand";
        }
        return "index-brand";
    }

    //회원가입 관리자/브랜드
    @PostMapping("/join")
    public String join(AdminRequest.JoinDTO reqDTO) {
        Admin admin = adminService.join(reqDTO);
        if (admin.getRole().equals(ADMIN)) {
            session.setAttribute("sessionAdmin", admin);
            return "index-admin";
        } else if (admin.getRole().equals(BRAND)) {
            session.setAttribute("sessionBrand", admin);
            return "index-brand";
        }
        return "index-brand";
    }

    // 회원가입 폼
    @GetMapping("/loginForm")
    public String loginForm() {
        return "/admin/login-form";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "/admin/join-form";
    }

    // 관리자 매출관리 페이지
    @GetMapping("/api/admin-sales-manage")
    public String adminSalesManage(HttpServletRequest req) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionAdmin");
        if (sessionAdmin == null) {
            throw new Exception403("잘못된 접근입니다.");
        }
        List<AdminResponse.SalesListDTO> orderHistoryList = adminService.adminSalesListDTOList();

        req.setAttribute("orderHistoryList", orderHistoryList);
        return "sales/admin-sales-manage";
    }

    // 브랜드 매출관리 페이지
    @GetMapping("/api/brand-sales-manage")
    public String brandSalesManage(HttpServletRequest req) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionBrand");
        List<AdminResponse.BrandOrderHistoryListDTO> orderHistoryList = adminService.brandOrderHistory(sessionAdmin.getId());
        req.setAttribute("orderHistoryList", orderHistoryList);

        return "sales/brand-sales-manage";
    }

    // 회원 관리 페이지
    @GetMapping("/api/user-manage")
    public String userManage(HttpServletRequest request) {
        List<UserResponse.UserListDTO> userList = adminService.getUserList();
        request.setAttribute("userList", userList);
        return "admin/user-manage";
    }

}
