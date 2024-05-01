package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.util.List;

public class PhotoResponse {

    @Data
    public static class HomeDTO {
        private List<CreatorPhotoDTO> creatorPhotos;
        private List<ItemsPhotoDTO> itemsPhotos;

        public HomeDTO(List<Photo> creatorPhotos, List<Photo> itemsPhotos) {
            this.creatorPhotos = creatorPhotos.stream().map(photo ->
                new CreatorPhotoDTO(photo, photo.getUser())).toList();

            this.itemsPhotos = itemsPhotos.stream().map(photo ->
                    new ItemsPhotoDTO(photo, photo.getItems(), photo.getItems().getAdmin())).toList();
        }

        @Data
        public class CreatorPhotoDTO{
            private Integer photoId;
            private String name;
            private String path;
            private Photo.Sort sort;
            private Integer creatorId;

            public CreatorPhotoDTO(Photo photo, User user) {
                this.photoId = photo.getId();
                this.name = photo.getName();
                this.path = photo.getPath();
                this.sort = photo.getSort();
                this.creatorId = user.getId();
            }
        }

        @Data
        public class ItemsPhotoDTO{
            private Integer photoId;
            private String name;
            private String path;
            private Photo.Sort sort;
            private ItemsDTO items;
            private AdminDTO adminInfo;

            public ItemsPhotoDTO(Photo photo, Items items, Admin admin) {
                this.photoId = photo.getId();
                this.name = photo.getName();
                this.path = photo.getPath();
                this.sort = photo.getSort();
                this.items = new ItemsDTO(items);
                this.adminInfo = new AdminDTO(admin);
            }

            @Data
            public class ItemsDTO{
                private Integer itemsId;
                private String name;

                public ItemsDTO(Items items) {
                    this.itemsId = items.getId();
                    this.name = items.getName();
                }
            }

            @Data
            public class AdminDTO{
                private Integer brandId;
                private String brandName;

                public AdminDTO(Admin admin) {
                    this.brandId = admin.getId();
                    this.brandName = admin.getBrandName();
                }
            }
        }
    }
}
