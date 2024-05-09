package com.example.finalproject.domain.love;

import lombok.Data;

public class LoveResponse {

    @Data
    public static class SaveUserLove{
        private Integer userId;
        private Integer codiId;
        private Boolean isloved;
        private Long loveCount;

        public SaveUserLove(Love love, Long loveCount) {
            this.userId = love.getUser().getId();
            this.codiId = love.getCodi().getId();
            this.isloved = love.getIsLoved();
            this.loveCount = loveCount;
        }
    }

    // 사용자번호와 좋아요 갯수 리스트 용
    @Data
    public static class UserLoveCount {
        private Integer userId;
        private Long loveCount;

        public UserLoveCount(Integer userId, Long loveCount) {
            this.userId = userId;
            this.loveCount = loveCount;
        }
    }

    // 코디번호와 좋아요 갯수 리스트 용
    @Data
    public static class CodiLoveCount {
        private Integer codiId;
        private Long loveCount;

        public CodiLoveCount(Integer codiId, Long loveCount) {
            this.codiId = codiId;
            this.loveCount = loveCount;
        }
    }

    // 코디번호와 사용자ID와 좋아요 갯수 그리고 좋아요 상태
    @Data
    public static class CodiUserLoveCheck {
        private Integer loveId;
        private Integer userId;
        private Boolean isLoved;

        public CodiUserLoveCheck(Integer loveId, Integer userId, Boolean isLoved) {
            this.loveId = loveId;
            this.userId = userId;
            this.isLoved = isLoved;
        }
    }
}
