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

    // 앱] 메인 화면 요청
    @GetMapping("/home-lists")
    public ResponseEntity<?> getHomeLists() {
        PhotoResponse.Home respDTO =  photoService.getHomeLists();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
