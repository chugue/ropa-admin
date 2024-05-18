package com.example.finalproject.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {

    // 프로필변경
    @Data
    public static class ProfileUpdateDTO {
        @NotEmpty(message = "실명이 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "닉네임은 최소 1자 이상 최대 20자 이하여야 합니다.")
        private String myName;
        @NotEmpty(message = "닉네임이 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "닉네임은 최소 1자 이상 최대 20자 이하여야 합니다.")
        private String nickName;
        private String mobile;
        @NotEmpty(message = "비밀번호가 공백일 수 없습니다.")
        @Size(min = 4, max = 20, message = "비밀번호는 최소 4자 이상 최대 20자 이하여야 합니다.")
        private String password;
        private PhotoDTO photo;

        public ProfileUpdateDTO(String myName, String nickName, String mobile, String password, PhotoDTO photo) {
            this.myName = myName;
            this.nickName = nickName;
            this.mobile = mobile;
            this.password = password;
            this.photo = photo;
        }

        @Data
        public static class PhotoDTO {
            private String name;
            private String base64;

            public PhotoDTO(String name, String base64) {
                this.name = name;
                this.base64 = base64;
            }
        }
    }

    //회원가입
    @NoArgsConstructor
    @Data
    public static class JoinDTO {
        @NotEmpty(message = "이메일이 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "이메일은 최소 1자 이상 최대 20자 이하여야 합니다.")
        private String email;

        @NotEmpty(message = "닉네임이 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "닉네임은 최소 1자 이상 최대 20자 이하여야 합니다.")
        private String nickName;

        @NotEmpty(message = "실명이 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "실명은 최소 1자 이상 최대 20자 이하여야 합니다.")
        private String myName;

        @NotEmpty(message = "비밀번호가 공백일 수 없습니다.")
        @Size(min = 4, max = 20, message = "비밀번호는 최소 4자 이상 최대 20자 이하여야 합니다.")
        private String password;

        public JoinDTO(String email, String nickName, String myName, String password) {
            this.email = email;
            this.nickName = nickName;
            this.myName = myName;
            this.password = password;
        }
    }

    // 앱용 로그인 요청
    @NoArgsConstructor
    @Data
    public static class LoginDTO {
        @NotEmpty(message = "이메일이 공백일 수 없습니다")
        @Email(message = "올바른 이메일 형식이어야 합니다")
        private String email;

        @NotEmpty(message = "비밀번호가 공백일 수 없습니다.")
        @Size(min = 4, max = 20, message = "비밀번호는 최소 4자 이상 최대 20자 이하여야 합니다.")
        private String password;

        @Builder
        public LoginDTO(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    // 앱 크리에이터 지원 요청
    @NoArgsConstructor
    @Data
    public static class CreatorApplyDTO {
        @NotEmpty(message = "키는 공백일 수 없습니다")
        private String height;

        @NotEmpty(message = "체중은 공백일 수 없습니다")
        private String weight;

        @NotEmpty(message = "SNS 주소가 공백일 수 없습니다.")
        private String instagram;

        @NotEmpty(message = "지원 내용은 공백일 수 없습니다.")
        private String comment;

        @NotEmpty(message = "직업은 공백일 수 없습니다.")
        private String job;

        public CreatorApplyDTO(String height, String weight, String instagram, String comment, String job) {
            this.height = height;
            this.weight = weight;
            this.instagram = instagram;
            this.comment = comment;
            this.job = job;
        }
    }

}
