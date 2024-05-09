package com.example.finalproject.domain.love;


import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoveRestController {
    private final LoveService loveService;
    private final HttpSession session;

    // 좋아요 상태변경 및 저장
    @PostMapping("/app/function/love")
    public ResponseEntity<?> love(@RequestBody LoveRequest.Save reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        LoveResponse.SaveUserLove respDTO = loveService.saveLove(reqDTO, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
