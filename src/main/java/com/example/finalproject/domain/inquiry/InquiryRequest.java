package com.example.finalproject.domain.inquiry;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

public class InquiryRequest {

    @Data
    public static class ReplyDTO {
        private Integer inquiryId;

        @NotEmpty(message = "뮨의 답변은 공백일 수 없습니다")
        @Size(min = 1, max = 20, message = "답변 길이는 최소 1자 이상 20장 이하여야 합니다")
        private String comment;
        private Timestamp commentedAt;
        private Boolean status;

        public ReplyDTO(Integer inquiryId, String comment, Boolean status) {
            this.inquiryId = inquiryId;
            this.comment = comment;
            this.commentedAt = Timestamp.from(Instant.now());
            this.status = status;
        }
    }

    @Data
    public static class SaveDTO {
        @NotNull(message = "해당 브랜드가 존재 하지 않습니다.")
        private Integer brandId;

        @NotEmpty(message = "뮨의 제목은 공백일 수 없습니다")
        @Size(min = 1, max = 20, message = "제목의 길이는 최소 1자 이상 20장 이하여야 합니다")
        private String title;

        @NotEmpty(message = "뮨의 내용은 공백일 수 없습니다")
        @Size(min = 1, max = 20, message = "내용의 길이는 최소 1자 이상 20장 이하여야 합니다")
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
