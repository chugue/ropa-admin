package com.example.finalproject.domain.inquiry;

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


        for (int i = respList.size(); i > 0; i--) {
            respList.get(i-1).setNum(i);
        }

        return respList;
    }
}
