package com.example.finalproject.domain.user;

import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.codi.CodiResponse;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.items.ItemsResponse;
import com.example.finalproject.domain.order.Order;
import com.example.finalproject.domain.photo.Photo;
import lombok.Data;
import org.hibernate.id.IntegralDataTypeHolder;

import java.util.List;

public class UserResponse {
        //유저 마이페이지
        @Data
        public static class UserMyPage {
            private Integer userId; //로그인한 유저 아이디
            private String photoName;   // 크리에이터 사진 이름
            private String base64;   // 크리에이터 사진 base64
            private String nickName;  //별명
            private Integer orderCount; // 주문 갯수

            public UserMyPage(User user, Integer orderCount){
                this.userId = user.getId();
                this.photoName = user.getPhoto().getUuidName();
                this.base64 = user.getPhoto().toBase64(user.getPhoto());
                this.nickName = user.getNickName();
                this.orderCount = orderCount;
            }
        }

    @Data
    public static class CreatorViewDTO {
        private CreatorInfo userDTO;
        private List<CodiList> codiList;
        private List<ItemList> itemList;

        public CreatorViewDTO(CreatorInfo userDTO, List<CodiList> codiList, List<ItemList> itemList) {
            this.userDTO = userDTO;
            this.codiList = codiList;
            this.itemList = itemList;
        }
    }

    @Data
    public static class CreatorInfo {
        private Integer creatorId; //크리에이터 아이디
        private Boolean blueChecked;    //true -> 크리에이터
        private String photoName;   // 크리에이터 사진 이름
        private String base64;   // 크리에이터 사진 base64
        private String nickName;    //별명
        private String height; //키
        private String weight;  // 체중
        private String job; //직업
        private String introMsg; //자기소개

        public CreatorInfo(User user) {
            this.creatorId = user.getId();
            this.blueChecked = user.getBlueChecked();
            this.photoName = user.getPhoto().getUuidName();
            this.base64 = user.getPhoto().toBase64(user.getPhoto());
            this.nickName = user.getNickName();
            this.height = user.getHeight();
            this.weight = user.getWeight();
            this.job = user.getJob();
            this.introMsg = user.getIntroMsg();
        }
    }

    @Data
    public static class CodiList {
        private Integer codiId;
        private Integer codiPhotoId;
        private String photoName;
        private String base64;
        private Photo.Sort codiPhoto;

        public CodiList(Codi codi) {
            this.codiId = codi.getId();
            List<Photo> codiPhotos = codi.getPhotos();
            if (codiPhotos != null && !codiPhotos.isEmpty()) {
                Photo codiPhoto = codiPhotos.get(0); // 첫 번째 포토만 사용
                this.codiPhotoId = codiPhoto.getId();
                this.photoName = codiPhoto.getUuidName();
                this.base64 = codiPhoto.toBase64(codiPhoto);
                this.codiPhoto = codiPhoto.getSort();
            }
        }
    }

    @Data
    public static class ItemList {
        private Integer itemId;
        private String name;
        private String description;
        private Integer price;
        private Integer itemPhotoId;
        private String itemPhotoName;
        private String base64;
        private Photo.Sort itemPhoto;

        public ItemList(Items items) {
            this.itemId = items.getId();
            this.name = items.getName();
            this.description = items.getDescription();
            this.price = items.getPrice();
            List<Photo> itemPhotos = items.getPhotos();
            if (itemPhotos != null && !itemPhotos.isEmpty()) {
                Photo itemPhoto = itemPhotos.get(0); // 첫 번째 포토만 사용
                this.itemPhotoId = itemPhoto.getId();
                this.itemPhotoName = itemPhoto.getUuidName();
                this.base64 = itemPhoto.toBase64(itemPhoto);
                this.itemPhoto = itemPhoto.getSort();
            }
        }
    }


    @Data  // 로그인 성공시 응답 DTO
    public static class LoginInfo {
        private String myName;
        private Integer id;
        private String email;
        private String username;
        private String mobile; // 연락처
        private String nickName; // 닉네임
        private String instagram; // 인스타그램
        private Boolean blueChecked;

        public LoginInfo(User user) {
            this.myName = user.getMyName();
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getMyName();
            this.mobile = user.getMobile();
            this.nickName = user.getNickName();
            this.instagram = user.getInstagram();
            this.blueChecked = user.getBlueChecked();
        }
    }

    // 관리자 유저 목록 DTO
    @Data
    public static class UserList {
        private String myName;
        private String email; // 아이디
        private String mobile; // 연락처
        private String nickName; // 닉네임
        private String instagram; // 인스타그램
        private Boolean isBlueChecked; // 크리에이터 인증

        public UserList(User user) {
            this.myName = user.getMyName();
            this.email = user.getEmail();
            this.mobile = user.getMobile();
            this.nickName = user.getNickName();
            this.instagram = user.getInstagram();
            this.isBlueChecked = user.getBlueChecked();
        }
    }

    @Data
    public static class JoinInfo {
        private Integer id;
        private String myName;
        private String email;
        private String nickName;
        private String mobile;
        private String status;
        private Boolean blueChecked;
        private String createdAt;

        public JoinInfo(User user) {
            this.id = user.getId();
            this.myName = user.getMyName();
            this.email = user.getEmail();
            this.nickName = user.getNickName();
            this.mobile = user.getMobile();
            this.status = user.getStatus();
            this.blueChecked = user.getBlueChecked();
            this.createdAt = user.getCreatedAt().toString();
        }
    }

    // 앱 사용자 세팅 DTO
    @Data
    public static class SettingPage {
        private Integer id;
        private String email;
        private String myName;
        private String nickName;
        private String mobile;

        public SettingPage(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.myName = user.getMyName();
            this.nickName = user.getNickName();
            this.mobile = user.getMobile();
        }
    }

    // 앱 사용자 프로필 DTO
    @Data
    public static class ProfilePage {
        private Integer userId;
        private String email;
        private String myName;
        private String nickName;
        private String mobile;
        private PhotoInfo photoDTO;

        public ProfilePage(User user, PhotoInfo photoDTO) {
            this.userId = user.getId();
            this.email = user.getEmail();
            this.myName = user.getMyName();
            this.nickName = user.getNickName();
            this.mobile = user.getMobile();
            this.photoDTO = photoDTO;
        }

        @Data
        public static class PhotoInfo {
            private Integer id;
            private String name;
            private String base64;

            public PhotoInfo(Photo photo) {
                this.id = photo.getId();
                this.name = photo.getUuidName();
                this.base64 = photo.toBase64(photo);
            }
        }
    }

    // 앱 크리에이터 지원 DTO
    @Data
    public static class CreatorApply {
        private Integer id;
        private String name;
        private String instagram;
        private Boolean blueChecked;
        private String status;

        public CreatorApply(User user) {
            this.id = user.getId();
            this.name = user.getMyName();
            this.instagram = user.getInstagram();
            this.blueChecked = user.getBlueChecked();
            this.status = user.getStatus();
        }
    }

    // 아이템, 코디 리스트 DTO
    @Data
    public static class SearchPage {
        private List<CodiResponse.CodiListDTO> codiListDTOS;
        private List<ItemsResponse.ItemListDTO> itemListDTOS;

        public SearchPage(List<CodiResponse.CodiListDTO> codiListDTOS, List<ItemsResponse.ItemListDTO> itemListDTOS) {
            this.codiListDTOS = codiListDTOS;
            this.itemListDTOS = itemListDTOS;
        }
    }
}

