package com.example.finalproject.inquiry;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import com.example.finalproject.domain.inquiry.Inquiry;
import com.example.finalproject.domain.inquiry.InquiryRepository;
import com.example.finalproject.domain.inquiry.InquiryRequest;
import com.example.finalproject.domain.inquiry.InquiryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@Import(InquiryService.class)
@DataJpaTest
public class InquiryServiceTest {
    @Autowired
    private InquiryService inquiryService;
    @Autowired
    private InquiryRepository inquiryRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void findByInquiryId_test(){
        // given
        Integer inquiryId = 1;

        // when
        Inquiry detail  = inquiryRepository.findByInquiryId(inquiryId);

        // then
        System.out.println(detail);

    }

    @Test
    public void inquiryReplyUpdate_test(){
        // given
        Integer id = 1;
        String comment = "야!!!!";
        Boolean status = true;
        InquiryRequest.ReplyDTO reqDTO
                = new InquiryRequest.ReplyDTO(id, comment, status);
        Optional<Admin> admin = adminRepository.findById(1);
        // when
        inquiryService.inquiryReplyUpdate(reqDTO, admin.get());
        Optional<Inquiry> inquiryOptional = inquiryRepository.findById(reqDTO.getInquiryId());
        // then
        if (inquiryOptional.isPresent()){
            System.out.println("수정된 답변 내용 : " + inquiryOptional.get().getComment());
        }
    }

//    @Test
//    public void findAllInquiryWithUser_test(){
//        // given
//        Integer adminId = 1;
//        // when
//        List<InquiryResponse.List> respList =
//                inquiryService.findAllInquiryWithUser(adminId);
//        // then
//        for (InquiryResponse.List resp : respList) {
//            System.out.println(resp.getNum());
//        }
//
//    }
}
