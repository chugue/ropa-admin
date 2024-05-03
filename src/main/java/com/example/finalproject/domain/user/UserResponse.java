package com.example.finalproject.domain.user;

import com.example.finalproject.domain.photo.Photo;
import lombok.Data;

public class UserResponse {

    @Data  // 로그인 성공시 응답 DTO
    public static class LoginDTO {
        private Integer id;
        private String email;
        private String username;
        private Boolean blueChecked;

        public LoginDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getMyName();
            this.blueChecked = user.getBlueChecked();
        }
    }

    @Data
    public static class UserListDTO {
        private String myName;
        private String email; // 아이디
        private String mobile; // 연락처
        private String nickName; // 닉네임
        private String instagram; // 인스타그램
        private Boolean isBlueChecked; // 크리에이터 인증

        public UserListDTO(User user) {
            this.myName = user.getMyName();
            this.email = user.getEmail();
            this.mobile = user.getMobile();
            this.nickName = user.getNickName();
            this.instagram = user.getInstagram();
            this.isBlueChecked = user.getBlueChecked();
        }
    }

    @Data
    public static class JoinDTO {
        private Integer id;
        private String nickName;
        private String createdAt;

        public JoinDTO(User user) {
            this.id = user.getId();
            this.nickName = user.getNickName();
            this.createdAt = user.getCreatedAt().toString();
        }
    }

    @Data
    public static class SettingPageDTO {
        private Integer id;
        private String email;
        private String myName;
        private String nickName;
        private String mobile;

        public SettingPageDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.myName = user.getMyName();
            this.nickName = user.getNickName();
            this.mobile = user.getMobile();
        }
    }

    @Data
    public static class ProfilePageDTO {
        private Integer userId;
        private String email;
        private String myName;
        private String nickName;
        private String mobile;
        private PhotoDTO photoDTO;

        public ProfilePageDTO(User user, PhotoDTO photoDTO) {
            this.userId = user.getId();
            this.email = user.getEmail();
            this.myName = user.getMyName();
            this.nickName = user.getNickName();
            this.mobile = user.getMobile();
            this.photoDTO = photoDTO;
        }

        @Data
        public static class PhotoDTO {
            private Integer id;
            private String name;
            private String path;

            public PhotoDTO(Photo photo) {
                this.id = photo.getId();
                this.name = photo.getName();
                this.path = photo.getPath();
            }
        }
    }

    @Data
    public static class CreatorApplyDTO {
        private Integer id;
        private String height;
        private String weight;
        private String instagram;
        private String job;

        public CreatorApplyDTO(User user) {
            this.id = user.getId();
            this.height = user.getHeight();
            this.weight = user.getWeight();
            this.instagram = user.getInstagram();
            this.job = user.getJob();
        }
    }
}
