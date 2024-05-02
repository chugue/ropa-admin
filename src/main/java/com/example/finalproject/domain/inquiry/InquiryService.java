package com.example.finalproject.domain.inquiry;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.admin.SessionAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InquiryService {
    private final InquiryRepository inquiryRepository;

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
    public void inquiryReplyUpdate(InquiryRequest.ReplyDTO reqDTO, SessionAdmin sessionAdmin) {
        Inquiry inquiry = inquiryRepository.findById(reqDTO.getInquiryId())
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다."));

        if (inquiry.getAdmin().getId() != sessionAdmin.getId()) {
            throw new Exception401("권한이 없습니다.");
        }
        inquiry.toReplyUpdate(reqDTO);
    }
}
