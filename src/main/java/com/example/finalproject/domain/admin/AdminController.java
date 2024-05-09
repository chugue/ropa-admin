package com.example.finalproject.domain.admin;

import com.example.finalproject._core.error.exception.Exception403;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.example.finalproject.domain.admin.Admin.AdminRole.ADMIN;
import static com.example.finalproject.domain.admin.Admin.AdminRole.BRAND;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;
    private final HttpSession session;


    //관리자 정보 페이지
    @GetMapping("/api/admin-information-form")
    public String adminInformation(HttpServletRequest req){
        Admin sessionAdmin = (Admin) session.getAttribute("sessionAdmin");
        Admin adminInfo = adminService.adminInfo(sessionAdmin.getId());
        req.setAttribute("adminInfo", adminInfo);
        return "admin/admin-information-form";
    }

    // 브랜드 정보 페이지
    @GetMapping("/api/brand-information-form")
    public String brandInformation(HttpServletRequest req){
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        if (sessionBrand == null) {
            throw new Exception403("잘못된 접근입니다.");
        }

        return "admin/brand-information-form";
    }


    //로그인
    @PostMapping("/login")
    public String login(AdminRequest.LoginDTO reqDTO, HttpSession session) {
        Admin admin = adminService.login(reqDTO);

        // 로그인 성공한 경우에만 세션을 설정하고 적절한 페이지로 리다이렉트
        if (admin.getRole().equals(Admin.AdminRole.ADMIN)) {
            session.setAttribute("sessionAdmin", admin);
            return "index-admin";
        } else if (admin.getRole().equals(Admin.AdminRole.BRAND)) {
            session.setAttribute("sessionBrand", admin);
            return "index-brand";
        }

        // 로그인 실패 시 기본 페이지로 리다이렉트하거나 에러 페이지로 처리할 수 있음
        return "index-brand";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(){
        session.removeAttribute("sessionAdmin");
        session.removeAttribute("sessionBrand");
        return "redirect:/";
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
    @GetMapping({"/", "/login-form"})
    public String loginForm() {
        return "/admin/login-form";
    }

    @GetMapping("/join-form")
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
        List<AdminResponse.UserListDTO> userList = adminService.getUserList();
        request.setAttribute("userList", userList);
        return "admin/user-manage";
    }

    @PostMapping("/approve-creators/{userId}")
    public String approveCreatorStatus(@PathVariable Integer userId) {
        adminService.approveCreatorStatus(userId);
        return "redirect:/api/user-manage";
    }

    @PostMapping("/reject-creators/{userId}")
    public String rejectCreatorStatus(@PathVariable Integer userId) {
        adminService.rejectCreatorStatus(userId);
        return "redirect:/api/user-manage";
    }
}