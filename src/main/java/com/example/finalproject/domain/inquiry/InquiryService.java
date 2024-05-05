package com.example.finalproject.domain.inquiry;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
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

    // 문의 등록
    @Transactional
    public InquiryResponse.SaveDTO saveInquiry(InquiryRequest.SaveDTO reqDTO, Integer userId) {
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

        return new InquiryResponse.SaveDTO(savedInquiry);

    }

    // 브랜드 관리자 ID로 모든 문의 조회
    public List<InquiryResponse.ListDTO> findAllInquiryWithUser(Integer adminId) {
        List<Inquiry> inquiryList = inquiryRepository.findAllByAdminIdWithUser(adminId);
        List<InquiryResponse.ListDTO> respList =
                inquiryList.stream().map(inquiry ->
                        new InquiryResponse.ListDTO(inquiry, inquiry.getUser())).toList();

        int a = 1;
        for (int i = respList.size(); i > 0; i--) {
            respList.get(i - 1).setNum(a);
            a++;
        }
        return respList;
    }

    // 문의 내용 확인하기
    public InquiryResponse.ReplyDTO findByInquiryId(Integer inquiryId) {
        Inquiry inquiry = inquiryRepository.findByInquiryIdWithUser(inquiryId)
                .orElseThrow(() -> new Exception404("해당 게시물을 찾을 수 없습니다."));

        return new InquiryResponse.ReplyDTO(inquiry, inquiry.getUser());
    }

    // 문의 답변 등록 또는 수정
    @Transactional
    public void inquiryReplyUpdate(InquiryRequest.ReplyDTO reqDTO, Admin sessionAdmin) {
        Inquiry inquiry = inquiryRepository.findById(reqDTO.getInquiryId())
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다."));

        if (inquiry.getAdmin().getId() != sessionAdmin.getId()) {
            throw new Exception401("권한이 없습니다.");
        }
        inquiry.toReplyUpdate(reqDTO);
    }

    // 문의 페이지 모든 문희 조회
    public List<InquiryResponse.UserPageDTO> inquiryPage(Integer userId) {
        List<Inquiry> inquiries = inquiryRepository.findAllByUserId(userId);

        return inquiries.stream().map(inquiry -> new InquiryResponse.UserPageDTO(inquiry)).toList();
    }




}
