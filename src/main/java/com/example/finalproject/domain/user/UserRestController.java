package com.example.finalproject.domain.user;


import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject._core.utils.AppJwtUtill;
import com.example.finalproject._core.utils.JwtVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;
    private final HttpSession session;

    // 앱] 로그인 요청
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        User user = userService.login(reqDTO);
        UserResponse.LoginDTO respDTO = new UserResponse.LoginDTO(user);
        String jwt = AppJwtUtill.create(user);
        return ResponseEntity.ok().header(JwtVO.HEADER, JwtVO.PREFIX + jwt).body(new ApiUtil<>(respDTO)); // header 문법
    }

    // 앱} 로그아웃
    @GetMapping("/user/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }

    // 앱] 회원가입
    @PostMapping("/user/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        User user = userService.join(reqDTO);
        String jwt = AppJwtUtill.create(user);
        UserResponse.JoinDTO respDTO = new UserResponse.JoinDTO(user);
        return ResponseEntity.ok().header(JwtVO.HEADER, JwtVO.PREFIX + jwt).body(new ApiUtil(respDTO));
    }

    // 앱 세팅 화면
    @GetMapping("/app/setting")
    public ResponseEntity<?> settingPage() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.SettingPageDTO respDTO = userService.settingPage(sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 앱 프로필 화면
    @GetMapping("/app/profile")
    public ResponseEntity<?> profilePage() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.ProfilePageDTO respDTO = userService.profilePage(sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 앱 사용자 크리에이터 지원 페이지
    @GetMapping("/app/creator-apply")
    public ResponseEntity<?> creatorApplyPage() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.CreatorApplyDTO respDTO = userService.creatorApplyPage(sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 앱 사용자 크리에이터 지원하기
    @PutMapping("/app/creator-apply")
    public ResponseEntity<?> creatorApply(@RequestBody UserRequest.CreatorApplyDTO creatorApplyDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.CreatorApplyDTO respDTO = userService.creatorApply(creatorApplyDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/app/creator/{id}")
    public ResponseEntity<?> creatorView(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.CreatorViewDTO respDTO = userService.creatorView(sessionUser, id);


        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
