package com.example.finalproject._core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............");

        //TODO User엔티티가 구현되면 주석 풀기 - jwt 는 모든 기능 완성후에
//        HttpSession session = request.getSession();
//
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        if(sessionUser == null){
//            throw new Exception401("로그인 하셔야 해요");
//        }
        return true;
    }

}
