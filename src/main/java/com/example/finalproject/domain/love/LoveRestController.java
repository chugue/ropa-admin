package com.example.finalproject.domain.love;


import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LoveRestController {
    private final LoveService loveService;
    private final HttpSession session;

    // 좋아요 상태변경 및 저장
    @PostMapping("/app/function/love/{codiId}")
    public ResponseEntity<?> loveSave(@PathVariable Integer codiId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        LoveResponse.SaveUserLove respDTO = loveService.saveLove(codiId, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 좋아요 삭제
    @DeleteMapping("/app/function/love/{codiId}")
    public ResponseEntity<?> loveDelete(@PathVariable Integer codiId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        LoveResponse.DeleteInfo respDTO = loveService.deleteLove(codiId, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
