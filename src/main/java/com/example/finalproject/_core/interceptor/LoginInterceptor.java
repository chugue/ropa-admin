package com.example.finalproject._core.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception500;
import com.example.finalproject._core.utils.JwtUtill;
import com.example.finalproject.domain.admin.SessionAdmin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............");

        //TODO User엔티티가 구현되면 주석 풀기 - jwt 는 모든 기능 완성후에
        String jwt = request.getHeader("Authorization");
        if (jwt != null) {
            jwt = jwt.replace("Bearer ", "");
        } else {
            // JWT가 없을 때 처리할 로직 작성
            throw new Exception401("JWT가 요청 헤더에 없습니다.");
        }

        HttpSession session = request.getSession();

        try {
            SessionAdmin sessionAdmin = JwtUtill.verify(jwt);
            if (sessionAdmin.getRole().equals("ADMIN")) {
                session.setAttribute("sessionAdmin", sessionAdmin);
            }
            if (sessionAdmin.getRole().equals("BRAND")) {
                session.setAttribute("sessionBrand", sessionAdmin);
            }
        } catch (TokenExpiredException e) {
            throw new Exception401("토큰 만료 시간이 지났습니다. 다시 로그인 하세요");
        } catch (JWTDecodeException e) {
            throw new Exception401("토큰이 유효하지 않습니다.");
        } catch (Exception e) {
            e.printStackTrace(); // 개발 진행 시 TEST 보기
            throw new Exception500(e.getMessage()); //알수 없는 오류는 다 500으로 던진다.
        }
        return true;
    }
}


