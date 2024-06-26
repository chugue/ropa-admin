package com.example.finalproject.domain.inquiry;


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
public class InquiryRestController {
    private final InquiryService inquiryService;
    private final HttpSession session;

    //문의 상세보기
    @GetMapping ("/app/inquiries/{inquiryId}")
    public ResponseEntity<?> inquiryDetail(@PathVariable int inquiryId){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        InquiryResponse.Detail respDTO = inquiryService.detailInquiry(sessionUser,inquiryId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
    // 문의 등록
    @PostMapping("/app/inquiries")
    public ResponseEntity<?> inquirySave(@Valid  @RequestBody InquiryRequest.SaveDTO reqDTO, Errors errors){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        InquiryResponse.Save respDTO = inquiryService.saveInquiry(reqDTO, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // sessionUser의 문의 목록 페이지
    @GetMapping("/app/inquiries-lists")
    public ResponseEntity<?> inquiryPage(){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<InquiryResponse.UserPage> respList = inquiryService.inquiryPage(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respList));
    }
}
