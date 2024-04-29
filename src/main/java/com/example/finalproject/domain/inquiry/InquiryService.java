package com.example.finalproject.domain.inquiry;

import com.example.finalproject._core.error.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            respList.get(i-1).setNum(a);
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
}
