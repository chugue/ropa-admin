package com.example.finalproject.domain.inquiry;

import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class InquiryResponse {

    @Data
    public static class ListDTO {
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

            if (inquiry.getStatus() == false) {
                this.statusMsg = "미응답";
            } else {
                this.statusMsg = "응답완료";
            }
        }
    }

    @Data
    public static class ReplyDTO {
        private Integer id;
        private String myName;
        private String title;
        private String content;
        private String comment;
        private String createdAt;
        private String commentedAt;

        public ReplyDTO(Inquiry inquiry, User user) {
            this.id = inquiry.getId();
            this.myName = user.getMyName();
            this.title = inquiry.getTitle();
            this.content = inquiry.getContent();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = Date.from(Instant.now());
            if (inquiry.getComment()==null){
                // 문의 답변이 없는 경우 해당 칸 비움
                this.comment = "";
                this.commentedAt = "";
            }else{
                // 답변이 있다면 화면에 출력
                this.comment = inquiry.getComment();
                this.commentedAt = dateFormat.format(inquiry.getCommentedAt());
            }
            this.createdAt = dateFormat.format(inquiry.getCreatedAt());
        }
    }
}
