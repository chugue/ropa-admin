package com.example.finalproject.domain.photo;

import com.example.finalproject._core.utils.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class PhotoRestController {
    private final PhotoService  photoService;

    // 검색 화면 페이지
    @GetMapping("/app/search-page")
    public ResponseEntity<?> searchPage(){
        PhotoResponse.SearchPage respDTO = photoService.findAllcodiAllItems();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 앱] 메인 화면 요청
    @GetMapping("/home-lists")
    public ResponseEntity<?> getHomeLists() {
        PhotoResponse.Home respDTO =  photoService.getHomeLists();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
