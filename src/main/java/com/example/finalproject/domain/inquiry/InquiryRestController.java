package com.example.finalproject.domain.inquiry;


import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class InquiryRestController {
    private final InquiryService inquiryService;
    private final HttpSession session;

    // sessionUser의 문의 목록 페이지
    @GetMapping("/app/inquiries")
    public ResponseEntity<?> inquiryPage(){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<InquiryResponse.UserPageDTO> respList = inquiryService.inquiryPage(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respList));
    }
}
