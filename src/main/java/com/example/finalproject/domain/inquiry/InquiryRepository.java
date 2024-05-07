package com.example.finalproject.domain.inquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
    //문의 정보와 답변 정보를 가져오기
    @Query("select i from Inquiry i join i.user u join fetch i.admin a where i.id = :inquiryId")
    Inquiry findByInquiryId(@Param("inquiryId") Integer inquiryId);


    // 브랜드 관리자 id 로 사용자 문의 모두 조회하기 생성일별 내림차순 정렬
    @Query("select i from Inquiry i join fetch i.user u where i.admin.id = :adminId order by i.createdAt desc")
    List<Inquiry> findAllByAdminIdWithUser(@Param("adminId") Integer adminId);

    // 문의 번호로 문의와 사용자 정보 가져오기
    @Query("select i from Inquiry i join fetch i.user u where i.id = :inquiryId")
    Optional<Inquiry> findByInquiryIdWithUser(@Param("inquiryId") Integer inquiryId);

    // 사용자 아이디로 검색되는 모든 문의 조회
    @Query("select i from Inquiry i where i.user.id = :userId")
    List<Inquiry> findAllByUserId(@Param("userId") Integer userId);
}
