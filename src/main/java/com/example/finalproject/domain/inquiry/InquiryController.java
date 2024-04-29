package com.example.finalproject.domain.inquiry;

import com.example.finalproject.domain.admin.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class InquiryController {
    private final HttpSession session;
    private final InquiryService inquiryService;

    // 문의 관리 페이지 - 목록 조회
    @GetMapping("/api/inquiry-manage")
    public String inquiryManage(HttpServletRequest req) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionAdmin");
        List<InquiryResponse.ListDTO> respList =
                inquiryService.findAllInquiryWithUser(sessionAdmin.getId());

        req.setAttribute("inquiryList", respList);
        return "inquiry/inquiry-manage";
    }

    // 답변 하기 폼 페이지
    @GetMapping("/api/inquiry-reply")
    public String inquiryReply() {
        return "inquiry/inquiry-reply-form";
    }

}
