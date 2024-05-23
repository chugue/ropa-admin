package com.example.finalproject._core.error;

import com.example.finalproject._core.error.exception.*;
import com.example.finalproject._core.utils.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(Exception400 e, HttpServletRequest request){
        log.warn("400 Error: {}, Request URI: {}", e.getMessage(), request.getRequestURI(), e);
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(Exception401 e, HttpServletRequest request){
        log.warn("401 Error: {}, Request URI: {}", e.getMessage(), request.getRequestURI(), e);
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(Exception403 e, HttpServletRequest request){
        log.error("403 Error: {}, Request URI: {}", e.getMessage(), request.getRequestURI(), e);
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(Exception404 e, HttpServletRequest request){
        log.warn("404 Error: {}, Request URI: {}", e.getMessage(), request.getRequestURI(), e);
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(Exception500 e, HttpServletRequest request){
        log.error("500 Error: {}, Request URI: {}", e.getMessage(), request.getRequestURI(), e);
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknown(Exception e, HttpServletRequest request){
        log.error("500 Error: {}, Request URI: {}", e.getMessage(), request.getRequestURI(), e);
        ApiUtil<?> apiUtil = new ApiUtil<>(500, ("오류 : 관리자에게 문의하세요"));
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
