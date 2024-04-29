package com.example.finalproject.inquiry;

import com.example.finalproject.domain.inquiry.InquiryResponse;
import com.example.finalproject.domain.inquiry.InquiryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(InquiryService.class)
@DataJpaTest
public class InquiryServiceTest {
    @Autowired
    private InquiryService inquiryService;

    @Test
    public void findAllInquiryWithUser_test(){
        // given
        Integer adminId = 1;
        // when
        List<InquiryResponse.ListDTO> respList =
                inquiryService.findAllInquiryWithUser(adminId);
        // then
        for (InquiryResponse.ListDTO resp : respList) {
            System.out.println(resp.getNum());
        }

    }
}
