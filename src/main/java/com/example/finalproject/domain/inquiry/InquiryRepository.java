package com.example.finalproject.domain.inquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {

    // 브랜드 관리자 id 로 사용자 문의 모두 조회하기 생성일별 내림차순 정렬
    @Query("select i from Inquiry i join fetch i.user u where i.admin.id = :adminId order by i.createdAt desc")
    List<Inquiry> findAllByAdminIdWithUser(@Param("adminId") Integer adminId);

    @Query("select i from Inquiry i join fetch i.user u where i.id = :inquiryId")
    Optional<Inquiry> findByInquiryIdWithUser(@Param("inquiryId") Integer inquiryId);
}
