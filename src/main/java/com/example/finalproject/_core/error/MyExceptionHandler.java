package com.example.finalproject._core.error;

import com.example.finalproject._core.error.exception.*;
import com.example.finalproject._core.utils.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(Exception400 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(Exception401 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(Exception403 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(Exception404 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(Exception500 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add additional exception handlers as needed

    @ControllerAdvice
    public static class MyViewExceptionHandler {

        @ExceptionHandler(Exception400.class)
        public String ex400(Exception400 e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.warn("400 : " + e.getMessage());
            return "err/400";
        }

        @ExceptionHandler(Exception401.class)
        public String ex401(Exception401 e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.warn("401 : " + e.getMessage());
            log.warn("IP : " + request.getRemoteAddr());
            log.warn("WAY : " + request.getHeader("User-Agent"));
            return "err/401";
        }

        @ExceptionHandler(Exception403.class)
        public String ex403(RuntimeException e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.warn("403 : " + e.getMessage());
            return "err/403";
        }

        @ExceptionHandler(Exception404.class)
        public String ex404(RuntimeException e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.info("404 : " + e.getMessage());
            return "/err/404";
        }

        @ExceptionHandler(Exception500.class)
        public String ex500(RuntimeException e, HttpServletRequest request){
            request.setAttribute("msg", e.getMessage());
            log.error("500 : " + e.getMessage());
            return "err/500";
        }

        // Add additional exception handlers as needed

    }
}
