package com.example.finalproject.domain.inquiry;

import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.text.SimpleDateFormat;

public class InquiryResponse {

    @Data
    public static class ListDTO{
        private Integer id; // 문의 PK
        private Integer num; // 문의게시물 개수 연산
        private String myName;
        private String title;
        private Boolean status;
        private String statusMsg;
        private String createdAt;

        public ListDTO(Inquiry inquiry, User user) {
            this.id = inquiry.getId();
            this.myName = user.getMyName();
            this.title = inquiry.getTitle();
            this.status = inquiry.getStatus();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.createdAt = dateFormat.format(inquiry.getCreatedAt());

            if (inquiry.getStatus() == false){
                this.statusMsg = "미응답";
            }else{
                this.statusMsg = "응답완료";
            }
        }
    }
}
