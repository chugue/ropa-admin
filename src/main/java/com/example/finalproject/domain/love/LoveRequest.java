package com.example.finalproject.domain.love;

import lombok.Data;

public class LoveRequest {

    @Data
    public static class UserLoveCount{
        private Integer userId;
        private Long loveCount;

        public UserLoveCount(Integer userId, Long loveCount) {
            this.userId = userId;
            this.loveCount = loveCount;
        }
    }

    @Data
    public static class CodiLoveCount{
        private Integer codiId;
        private Long loveCount;

        public CodiLoveCount(Integer codiId, Long loveCount) {
            this.codiId = codiId;
            this.loveCount = loveCount;
        }
    }
}
