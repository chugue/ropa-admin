package com.example.finalproject.domain.inquiry;

import com.example.finalproject.domain.admin.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class InquiryController {
    private final HttpSession session;
    private final InquiryService inquiryService;

    // 문의 관리 페이지 - 목록 조회
    @GetMapping("/api/inquiry-manage")
    public String inquiryManage(HttpServletRequest req, String searchBy, @RequestParam(defaultValue = "") String keyword) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        List<InquiryResponse.List> respList =
                inquiryService.findAllInquiryWithUser(sessionBrand.getId(), searchBy, keyword);
        req.setAttribute("inquiryList", respList);
        return "inquiry/inquiry-manage";
    }

    // 문의 내용 답변 폼 페이지
    @GetMapping("/api/inquiry-reply/{inquiryId}")
    public String inquiryReply(@PathVariable(name = "inquiryId") Integer inquiryId, HttpServletRequest req) {
        InquiryResponse.Reply respDTO = inquiryService.findByInquiryId(inquiryId);
        req.setAttribute("inquiryReply", respDTO);
        return "inquiry/inquiry-reply-form";
    }

    // 문의 답변 업데이트
    @PostMapping("/api/inquiry-reply-update")
    public String inquiryReplyUpdate(@Valid InquiryRequest.ReplyDTO reqDTO) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionBrand");
        inquiryService.inquiryReplyUpdate(reqDTO, sessionAdmin);
        return "redirect:/api/inquiry-reply/" + reqDTO.getInquiryId();
    }
}