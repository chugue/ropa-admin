package com.example.finalproject.domain.photo;

import lombok.Data;

import java.util.List;

public class PhotoResponse {

    @Data
    public static class HomeDTO {
        private List<Photo> creatorPhoto;
        private List<Photo> itemsPhoto;

        public HomeDTO(List<Photo> creatorPhoto, List<Photo> itemsPhoto) {
            this.creatorPhoto = creatorPhoto;
            this.itemsPhoto = itemsPhoto;
        }
    }
}
