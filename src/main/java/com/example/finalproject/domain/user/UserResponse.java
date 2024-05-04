package com.example.finalproject.domain.user;

import com.example.finalproject.domain.codiItems.CodiItems;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.photo.Photo;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserResponse {
    @Data // 인기 크리에이터의 정보를 보여줄 때 사용되는 응답 DTO
    public static class CreatorViewDTO {
        private Integer userId;
        private UserDTO userDTO;
        private List<CodiListDTO> codiList;
        private List<ItemListDTO> itemList;

        public CreatorViewDTO(User user, List<CodiItems> codiItems, List<Items> itemsList) {
            this.userId = user.getId();
            this.userDTO = new UserDTO(user);
            // 코디 아이템 리스트 매핑
            this.codiList = codiItems.stream()
                    .map(CodiListDTO::new)
                    .collect(Collectors.toList());

            // itemList 초기화
            // 아이템 리스트 매핑
            this.itemList = itemsList.stream()
                    .map(item -> new ItemListDTO(item))
                    .collect(Collectors.toList());


            // 중복 아이템 제외하며 itemList 초기화
            Set<Integer> uniqueItemIds = new HashSet<>();
            for (CodiItems codiItem : codiItems) {
                if (uniqueItemIds.add(codiItem.getItems().getId())) {
                    this.itemList.add(new ItemListDTO(ite, ));
                }
            }
        }


        @Data
        public class UserDTO {
            private Integer creatorId; //크리에이터 아이디
            private Boolean blueChecked;    //true -> 크리에이터
            private String photoName;   // 크리에이터 사진 이름
            private String photoPath;   // 크리에이터 사진 경로
            private String nickName;    //별명
            private String height; //키
            private String weight;  // 체중
            private String job; //직업
            private String introMsg; //자기소개

            public UserDTO(User user) {
                this.creatorId = user.getId();
                this.blueChecked = user.getBlueChecked();
                this.photoName = user.getPhoto().getName();
                this.photoPath = user.getPhoto().getPath();
                this.nickName = user.getNickName();
                this.height = user.getHeight();
                this.weight = user.getWeight();
                this.job = user.getJob();
                this.introMsg = user.getIntroMsg();
            }
        }

        @Data
        public class CodiListDTO {
            private Integer codiId;
            private Integer codiPhotoId;
            private String photoName;
            private String photoPath;
            private Photo.Sort codiPhoto;


            public CodiListDTO(CodiItems codiItems) {
                this.codiId = codiItems.getCodi().getId();
                this.codiPhotoId = codiItems.getCodi().getPhotos().getFirst().getId();
                this.photoName = codiItems.getCodi().getPhotos().iterator().next().getName();
                this.photoPath = codiItems.getCodi().getPhotos().iterator().next().getPath();
                this.codiPhoto = codiItems.getCodi().getPhotos().iterator().next().getSort();
            }
        }

        @Data
        public class ItemListDTO {
            private Integer itemId;
            private String name;
            private String description;
            private Integer price;
            private String photoName;
            private String photoPath;
            private Photo.Sort itemPhoto;

            public ItemListDTO(Items items) {
                this.itemId = items.getId();
                this.name = items.getName();
                this.description = items.getDescription();
                this.price = items.getPrice();
                if (!items.getPhotos().isEmpty()) {
                    Photo itemPhoto = items.getPhotos().iterator().next();
                    this.photoName = itemPhoto.getName();
                    this.photoPath = itemPhoto.getPath();
                    this.itemPhoto = itemPhoto.getSort();
                }
            }
        }


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

        // 관리자 유저 목록 DTO
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

        // 앱 사용자 세팅 DTO
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

        // 앱 사용자 프로필 DTO
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

        // 앱 크리에이터 지원 DTO
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
}