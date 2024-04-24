package com.example.finalproject.domain.inquiry;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class InquiryController {
    private final HttpServletRequest request;
    private final HttpSession session;

    // 문의 관리 페이지
    @GetMapping("/api/inquiry-manage")
    public String inquiryManage() {
        return "inquiry/inquiry-manage";
    }

    // 답변 하기 폼 페이지
    @GetMapping("/api/inquiry-reply")
    public String inquiryReply() {
        return "inquiry/inquiry-reply-form";
    }

}
