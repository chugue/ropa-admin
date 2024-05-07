package com.example.finalproject.domain.inquiry;

import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class InquiryResponse {

    //문의 상세보기 DTO
    @Data
    public static class DetailDTO {
        private Integer inquiryId;
        private Integer userId;
        private String title;
        private String content;
        private Timestamp createAt;
        private CommentDTO commentDTO;

        public DetailDTO(Inquiry inquiry, CommentDTO commentDTO) {
            this.inquiryId = inquiry.getId();
            this.userId = inquiry.getUser().getId();
            this.title = inquiry.getTitle();
            this.content = inquiry.getContent();
            this.createAt = inquiry.getCreatedAt();
            this.commentDTO = commentDTO;
        }

        //브랜드의 답변  DTO
        @Data
        public static class CommentDTO{
            private Integer  brandId;
            private String brandName;
            private Boolean status;
            private String comment;
            private Timestamp commentedAt;

            public CommentDTO(Inquiry inquiry) {
                this.brandId = inquiry.getAdmin().getId();
                this.brandName = inquiry.getAdmin().getBrandName();
                this.status = inquiry.getStatus();
                this.comment = inquiry.getComment();
                this.commentedAt = inquiry.getCommentedAt();
            }
        }
    }

    @Data // 문의 저장후 확인 데이터
    public static class SaveDTO {
        private Integer inquiryId;
        private Integer userId;
        private Integer brandId;
        private String title;
        private Timestamp createdAt;

        public SaveDTO(Inquiry inquiry) {
            this.inquiryId = inquiry.getId();
            this.userId = inquiry.getUser().getId();
            this.brandId = inquiry.getAdmin().getId();
            this.title = inquiry.getTitle();
            this.createdAt = inquiry.getCreatedAt();
        }
    }


    @Data   // 앱 문의화면에서 뿌려지는 문의 목록 DTO
    public static class UserPageDTO {
        private Integer inquiryId;
        private Boolean isReplied;
        private String title;
        private String content;
        private String createdAt;

        public UserPageDTO(Inquiry inquiry) {
            this.inquiryId = inquiry.getId();
            this.isReplied = inquiry.getStatus();
            this.title = inquiry.getTitle();
            this.content = inquiry.getContent();
            this.createdAt = formatDate(inquiry.getCreatedAt());
        }

        // TimeStamp를 특정 포맷으로 변환 : 여기선 2024-02-21 15:30 형태
        private String formatDate(Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return dateFormat.format(date);
        }
    }


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
        private Boolean isReplied;

        private String commentedAt;
        public ReplyDTO(Inquiry inquiry, User user) {
            this.id = inquiry.getId();
            this.myName = user.getMyName();
            this.title = inquiry.getTitle();
            this.content = inquiry.getContent();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = Date.from(Instant.now());
            if (inquiry.getComment().equals("")) {
                // 문의 답변이 없는 경우 해당 칸 비움
                this.comment = "";
                this.commentedAt = "";
                this.isReplied = false;
            } else {
                // 답변이 있다면 화면에 출력
                this.comment = inquiry.getComment();
                this.commentedAt = dateFormat.format(date);
                this.isReplied = true;
            }
            this.createdAt = dateFormat.format(inquiry.getCreatedAt());
        }

    }


}
