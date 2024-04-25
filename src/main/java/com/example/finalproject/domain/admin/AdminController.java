package com.example.finalproject.domain.admin;

import com.example.finalproject._core.util.ApiUtil;
import com.example.finalproject.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;
    private final HttpSession session;


    @PostMapping("login")
   public String login(AdminRequest.LoginDTO reqDTO, HttpServletRequest req){
        Admin admin = adminService.login(reqDTO);
        req.setAttribute("Admin", admin);
        return "index";
    }
    @PostMapping("/join")
    public String join(AdminRequest.JoinDTO reqDTO, HttpServletRequest req){
        Admin admin = adminService.join(reqDTO);
        req.setAttribute("Admin", admin);
        return "/admin/join-form";
    }

    //아이디 중복체크
    @GetMapping("/api/email-same-check")
    public @ResponseBody ApiUtil<?> eamilSameCheck(String email){
        Optional<Admin> admin = adminService.emailSameCheck(email);
        return admin.isEmpty() ? new ApiUtil<>(true) : new ApiUtil<>(false);
    }


    @GetMapping("/loginForm")
    public String loginForm(){
        return "admin/login-form";
    }
    @GetMapping("/joinForm")
    public String joinForm(){
        return "/admin/join-form";
    }

}
