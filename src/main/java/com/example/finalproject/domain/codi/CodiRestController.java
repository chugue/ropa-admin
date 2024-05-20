package com.example.finalproject.domain.codi;

import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CodiRestController {
    private final CodiService codiService;
    private final HttpSession session;


    // 앱] 코디 등록 ✅
    @PostMapping("/app/codi-register")
    public ResponseEntity<?> codiRegister(@Valid @RequestBody CodiRequest.SaveDTO reqDTO, Errors errors) {
        CodiResponse.SavedCodi respDTO = codiService.saveCodiAndItems(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 코디 수정 페이지 ✅
    @GetMapping("/app/codi-update-page/{codiId}")
    public ResponseEntity<?> updatePage(@PathVariable Integer codiId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        CodiResponse.UpdatePage respDTO = codiService.findInfoByCodiId(codiId, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //앱] 코디 등록 - 아이템 연결 페이지 ✅
    @GetMapping("/app/codi-register/add-item/{category}")
    public ResponseEntity<?> topItemSave(@PathVariable String category) {
        List<CodiResponse.BrandInfo> respDTO = codiService.addItemPage(category);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 코디 보기 페이지 (페이지내 아이템 목록 코디목록있음) ✅
    @GetMapping("/app/codi-pages/{codiId}")
    public ResponseEntity<?> codiPage(@PathVariable Integer codiId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        CodiResponse.MainView respDTO = codiService.codiPage(codiId, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 공개된 코디 보기 페이지 (좋아요 false) - 로그인 안해도 볼 수 있음 ✅
    @GetMapping("/codi-pages/{codiId}")
    public ResponseEntity<?> codiOpenPage(@PathVariable Integer codiId) {
        CodiResponse.OpenMainView respDTO = codiService.codiOpenPage(codiId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 유저 코디 검색 기능
    @GetMapping("/app/search-codi")
    public ResponseEntity<?> searchCodi(@RequestParam(defaultValue = "") String keyword) {
        List<CodiResponse.CodiListDTO> respDTO = codiService.searchCodi(keyword.trim());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
