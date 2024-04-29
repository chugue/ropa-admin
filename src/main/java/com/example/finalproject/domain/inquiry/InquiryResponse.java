package com.example.finalproject.domain.inquiry;

import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.sql.Timestamp;

public class InquiryResponse {

    @Data
    public static class ListDTO{
        private Integer id; // 문의 PK
        private Integer num; // 문의게시물 개수 연산
        private String myName;
        private String title;
        private Boolean status;
        private Timestamp createdAt;

        public ListDTO(Inquiry inquiry, User user) {
            this.id = inquiry.getId();
            this.myName = user.getMyName();
            this.title = inquiry.getTitle();
            this.status = inquiry.getStatus();
            this.createdAt = inquiry.getCreatedAt();
        }
    }
}
