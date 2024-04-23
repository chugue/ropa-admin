package com.example.finalproject.domain.admin;

import com.example.finalproject.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final HttpServletRequest request;

    @PostMapping("/join")
    public String join (){
        Admin admin = AdminService.;
    }

}
