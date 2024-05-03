package com.example.finalproject._core.interceptor;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject.domain.admin.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............");

        HttpSession session = request.getSession();

        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        Admin sessionAdmin = (Admin) session.getAttribute("sessionAdmin");
        if(sessionBrand != null || sessionAdmin != null){
            return true;
        }
        throw new Exception401("로그인 하셔야 해요");
    }
}
