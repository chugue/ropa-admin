package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;
    private final HttpSession session;

//    @PostMapping("login")
//   public String login ()
    @PostMapping("/join")
    public String join(AdminRequest.JoinDTO reqDTO, HttpServletRequest req){
        Admin admin = adminService.join(reqDTO);
        req.setAttribute("Admin", admin);
        return "/admin/join-form";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "admin/login-form";
    }
    @GetMapping("/joinForm")
    public String joinForm(){
        return "/admin/join-form";
    }

}
