package com.itwill.igojoamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class PlaceImageDto {
        private String placeName;
        private List<MultipartFile> images;
        private List<String> imageNames;
        private List<String> imageUrls;
}
