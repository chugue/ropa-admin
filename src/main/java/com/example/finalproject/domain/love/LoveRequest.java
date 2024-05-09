package com.example.finalproject.domain.love;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class LoveRequest {

    @Data
    public static class Save {
        private Integer codiId;

        @JsonCreator
        public Save(@JsonProperty("codiId") Integer codiId) {
            this.codiId = codiId;
        }
    }
}
