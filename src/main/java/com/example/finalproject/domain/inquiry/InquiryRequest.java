package com.example.finalproject.domain.inquiry;

import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

public class InquiryRequest {

    @Data
    public static class ReplyDTO {
        private Integer inquiryId;
        private String comment;
        private Timestamp commentedAt;

        public ReplyDTO(Integer inquiryId, String comment) {
            this.inquiryId = inquiryId;
            this.comment = comment;
            this.commentedAt = Timestamp.from(Instant.now());
        }
    }

    @Data
    public static class SaveDTO {
        private Integer brandId;
        private String title;
        private String content;
        private Timestamp createdAt;

        public SaveDTO(Integer brandId, String title, String content) {
            this.brandId = brandId;
            this.title = title;
            this.content = content;
            this.createdAt = Timestamp.from(Instant.now());
        }
    }
}
