package com.example.finalproject.domain.admin;

import com.example.finalproject._core.error.exception.Exception403;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
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
    public String login(@Valid AdminRequest.LoginDTO reqDTO) {
        Admin admin = adminService.login(reqDTO);
        if (admin.getRole().equals(ADMIN)) {
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
    public String join(@Valid AdminRequest.JoinDTO reqDTO) {
        Admin admin = adminService.join(reqDTO);

        session.setAttribute("sessionBrand", admin);

        return "index-brand";
    }

    // 로그인 폼
    @GetMapping({"/", "/login-form"})
    public String loginForm() {
        return "/admin/login-form";
    }

    // 회원가입 폼
    @GetMapping("/join-form")
    public String joinForm() {
        return "/admin/join-form";
    }

    // 회원정보 수정 폼 및 정보 확인
    @GetMapping("/api/admin-update-form")
    public String updateForm(HttpServletRequest req) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        if (sessionBrand == null) {
            throw new Exception403("잘못된 접근입니다.");
        }
        AdminResponse.UserInfo userInfo = adminService.getUserInfo(sessionBrand.getId());
        req.setAttribute("userInfo", userInfo);
        return "/admin/update-form";
    }

    // 회원정보 수정
    @PostMapping("/api/admin-update")
    public String update(AdminRequest.UpdateDTO reqDTO) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        adminService.update(reqDTO, sessionBrand.getId());
        return "index-brand";
    }

    // 관리자 매출관리 페이지
    @GetMapping("/api/admin-sales-manage")
    public String adminSalesManage(String searchBy, @RequestParam(defaultValue = "") String keyword, HttpServletRequest req) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionAdmin");
        if (sessionAdmin == null) {
            throw new Exception403("잘못된 접근입니다.");
        }
        AdminResponse.AdminSalesManagement adminSalesManagement = adminService.adminSalesListDTOList(searchBy, keyword);

        req.setAttribute("adminSalesManagement", adminSalesManagement);
        return "sales/admin-sales-manage";
    }

    // 브랜드 매출관리 페이지
    @GetMapping("/api/brand-sales-manage")
    public String brandSalesManage(@RequestParam(value = "startDate", required = false) LocalDateTime startDate,
                                   @RequestParam(value = "endDate", required = false) LocalDateTime endDate,
                                   HttpServletRequest reqDTO) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionBrand");
        AdminResponse.BrandSalesManagement brandSalesManagement = adminService.brandOrderHistory(sessionAdmin.getId(), startDate, endDate);
        reqDTO.setAttribute("brandSalesManagement", brandSalesManagement);
        return "sales/brand-sales-manage";
    }

    // 회원 관리 페이지
    @GetMapping("/api/user-manage")
    public String userManage(String searchBy, @RequestParam(defaultValue = "") String keyword, HttpServletRequest request) {
        List<AdminResponse.UserList> userList = adminService.getUserList(searchBy, keyword);
        request.setAttribute("userList", userList);
        return "admin/user-manage";
    }

    // 크리에이터 관리 페이지
    @GetMapping("/api/creator-manage")
    public String creatorManage(String searchBy, @RequestParam(defaultValue = "") String keyword, HttpServletRequest req) {
        List<AdminResponse.CreatorList> creatorList = adminService.creatorList(searchBy, keyword);
        req.setAttribute("creatorList", creatorList);
        return "admin/creator-manage";
    }


    @PostMapping("/approve-creators/{userId}")
    public String approveCreatorStatus(@PathVariable Integer userId) {
        adminService.approveCreatorStatus(userId);
        return "redirect:/api/creator-manage";
    }

    @PostMapping("/reject-creators/{userId}")
    public String rejectCreatorStatus(@PathVariable Integer userId) {
        adminService.rejectCreatorStatus(userId);
        return "redirect:/api/user-manage";
    }
}