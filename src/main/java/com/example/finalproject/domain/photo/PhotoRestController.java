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

    // 검색 페이지 기본 정보 요청 : 코디탭 + 아이템 탭
    @GetMapping("/app/main-search-page")
    public ResponseEntity<?> searchPage () {
        PhotoResponse.GetSearchPage respDTO = photoService.getSearchPage();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 앱] 메인 화면 요청
    @GetMapping("/home-lists")
    public ResponseEntity<?> getHomeLists() {
        PhotoResponse.Home respDTO =  photoService.getHomeLists();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
