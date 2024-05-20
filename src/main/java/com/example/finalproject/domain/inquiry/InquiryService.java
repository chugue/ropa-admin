package com.example.finalproject.domain.inquiry;

import com.example.finalproject._core.error.exception.*;
import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import com.example.finalproject.domain.user.SessionUser;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    // 문의 상세보기(브랜드가 문읟 답변 하는 페이지)
    public InquiryResponse.Detail detailInquiry(SessionUser sessionUser, int inquiryId) {
        Admin admin = adminRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception403("페이지의 접근 권한이없습니다."));

        Inquiry inquiry = inquiryRepository.findByInquiryId(inquiryId)
                .orElseThrow(() -> new Exception404("페이지를 찾을 수 업습니다."));

        return new InquiryResponse.Detail(inquiry, new InquiryResponse.Detail.Comment(inquiry));

    }

    // 문의 등록
    @Transactional
    public InquiryResponse.Save saveInquiry(InquiryRequest.SaveDTO reqDTO, Integer userId) {
        Admin admin = adminRepository.findById(reqDTO.getBrandId())
                .orElseThrow(() -> new Exception404("업체를 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("등록되지 않은 사용자 입니다."));

        Inquiry savedInquiry = inquiryRepository.save(Inquiry.builder()
                .user(user)
                .admin(admin)
                .title(reqDTO.getTitle())
                .content(reqDTO.getContent())
                .createdAt(reqDTO.getCreatedAt())
                .status(false).build());

        return new InquiryResponse.Save(savedInquiry);
    }

    // 브랜드 관리자 ID로 모든 문의 조회
    public List<InquiryResponse.List> findAllInquiryWithUser(Integer adminId, String searchBy, String keyword) {

        List<Inquiry> inquiryList = switch (searchBy) {
            case "username" -> inquiryRepository.findAllByAdminIdWithUserAndUsername(adminId, keyword);
            case "title" -> inquiryRepository.findAllByAdminIdWithUserAndTitle(adminId, keyword);
            case null, default -> inquiryRepository.findAllByAdminIdWithUser(adminId);
        };

        List<InquiryResponse.List> respList =
                inquiryList.stream().map(inquiry ->
                        new InquiryResponse.List(inquiry, inquiry.getUser())).toList();

        int a = 1;
        for (int i = respList.size(); i > 0; i--) {
            respList.get(i - 1).setNum(a);
            a++;
        }
        return respList;
    }

    // 문의 내용 확인하기
    public InquiryResponse.Reply findByInquiryId(Integer inquiryId) {
        Inquiry inquiry = inquiryRepository.findByInquiryIdWithUser(inquiryId)
                .orElseThrow(() -> new SSRException404("해당 게시물을 찾을 수 없습니다."));

        return new InquiryResponse.Reply(inquiry, inquiry.getUser());
    }

    // 문의 답변 등록 또는 수정
    @Transactional
    public void inquiryReplyUpdate(InquiryRequest.ReplyDTO reqDTO, Admin sessionAdmin) {
        Inquiry inquiry = inquiryRepository.findById(reqDTO.getInquiryId())
                .orElseThrow(() -> new SSRException404("해당 게시글을 찾을 수 없습니다."));

        if (inquiry.getAdmin().getId() != sessionAdmin.getId()) {
            throw new Exception401("권한이 없습니다.");
        }
        inquiry.toReplyUpdate(reqDTO);
    }

    // 문의 페이지 모든 문희 조회
    public List<InquiryResponse.UserPage> inquiryPage(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception400("해당 문의 목록 조회 권한이 없습니다."));
        List<Inquiry> inquiries = inquiryRepository.findAllByUserId(user.getId());
        return inquiries.stream().map(InquiryResponse.UserPage::new).toList();
    }
}
