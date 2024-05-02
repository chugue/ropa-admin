package com.example.finalproject.domain.codi;

import com.example.finalproject._core.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class CodiController {
    private final CodiService codiService;

    // 코디 보기 페이지 (페이지내 아이템 목록 코디목록있음)
    @GetMapping("/app/codi-pages/{codiId}")
    public ResponseEntity<?> codiPage(@PathVariable Integer codiId){
        CodiResponse.MainViewDTO respDTO = codiService.codiPage(codiId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }


    // 코디 관리 페이지
    @GetMapping("/api/codi-manage")
    public String codiManage() {
        return "codi/codi-manage";
    }

    // 코디 등록 페이지
    @GetMapping("/api/codi-register-form")
    public String codiRegisterForm() {
        return "codi/codi-register-form";
    }

}
