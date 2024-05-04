package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.admin.Admin;
import lombok.Data;

import java.util.List;

public class PhotoResponse {

    @Data
    public static class HomeDTO {
        private List<CreatorPhotoDTO> popularUserPhotos;
        private List<ItemsPhotoDTO> popularItemsPhotos;
        private List<CodiesPhotoDTO> popularCodiPhotos;

        public HomeDTO(List<Photo> popularUserPhotos, List<Photo> popularItemsPhotos, List<Photo> popularCodiPhotos) {
            this.popularUserPhotos = popularUserPhotos.stream().map(CreatorPhotoDTO::new).toList();
            this.popularItemsPhotos = popularItemsPhotos.stream().map(photo ->
                    new ItemsPhotoDTO(photo, photo.getItems().getAdmin())).toList();
            this.popularCodiPhotos = popularCodiPhotos.stream().map(CodiesPhotoDTO::new).toList();
        }


        @Data
        public class CreatorPhotoDTO{
            private Integer photoId;
            private String name;
            private String path;
            private Photo.Sort sort;
            private Integer creatorId;

            public CreatorPhotoDTO(Photo photo) {
                this.photoId = photo.getId();
                this.name = photo.getName();
                this.path = photo.getPath();
                this.sort = photo.getSort();
                this.creatorId = photo.getUser().getId();
            }
        }

        @Data
        public class ItemsPhotoDTO{
            private Integer photoId;
            private String name;
            private String path;
            private Photo.Sort sort;
            private Integer itemsId;
            private AdminDTO adminInfo;

            public ItemsPhotoDTO(Photo photo, Admin admin) {
                this.photoId = photo.getId();
                this.name = photo.getName();
                this.path = photo.getPath();
                this.sort = photo.getSort();
                this.itemsId = photo.getItems().getId();
                this.adminInfo = new AdminDTO(admin);
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

        @Data
        public class CodiesPhotoDTO {
            private Integer photoId;
            private String name;
            private String path;
            private Photo.Sort sort;
            private Integer codiId;

            public CodiesPhotoDTO(Photo photo) {
                this.photoId = photo.getId();
                this.name = photo.getName();
                this.path = photo.getPath();
                this.sort = photo.getSort();
                this.codiId = photo.getId();
            }
        }
    }
}
