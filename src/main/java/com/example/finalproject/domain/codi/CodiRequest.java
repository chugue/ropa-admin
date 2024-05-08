package com.example.finalproject.domain.codi;

import lombok.Data;

import java.util.List;

public class CodiRequest {

    @Data
    public static class SaveDTO {
        private Integer userId;
        private String title;
        private String description;
        private List<CodiRequest.SaveDTO>  

    }
}
