package com.example.finalproject._core.error;

import com.example.finalproject._core.error.exception.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;


@Aspect // AOP 등록
@Component // IoC 등록
public class MyValidationHandler {
//    com/example/finalproject/domain/codi/CodiService.java

    @Before("execution(* com.example.finalproject.domain.codi.CodiService.searchCodi(..)) && args(keyword)")
    public void validateKeyword(JoinPoint joinPoint, String keyword) {
       if (keyword.length() > 10) {
           throw new Exception400("검색어는 10글자 이하로 입력해주세요.");
       }
    }

    @Before("execution(* com.example.finalproject.domain.items.ItemsService.searchItems(..)) && args(keyword)")
    public void validateItemKeyword(JoinPoint joinPoint, String keyword) {
        if (keyword.length() > 10) {
            throw new Exception400("검색어는 10글자 이하로 입력해주세요.");
        }
    }

    // Advice (부가 로직 메서드)
    // Advice가 수행될 위치 == PointCut
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void validCheck(JoinPoint jp){
        Object[] args = jp.getArgs(); // 파라메터(매개변수)

        for(Object arg : args){
            if(arg instanceof Errors){
                Errors errors = (Errors) arg;

                if(errors.hasErrors()){
                    for (FieldError error : errors.getFieldErrors()){
                        throw new Exception400(error.getDefaultMessage()+" : "+error.getField());
                    }
                }
            }

        }
    }
}
