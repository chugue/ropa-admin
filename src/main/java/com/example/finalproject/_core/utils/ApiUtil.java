package com.example.finalproject._core.utils;

import lombok.Data;

@Data
public class ApiUtil<T> {
    private Boolean success;
    private T response;
    private Integer status;
    private String errorMessage;

    // 성공시
    public ApiUtil(T response) {
        this.success = true;
        this.response = response;
        this.status = 200;
        this.errorMessage = null;
    }

    // 실패시
    public ApiUtil(Integer status, String errorMessage) {
        this.success = false;
        this.response = null;
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
