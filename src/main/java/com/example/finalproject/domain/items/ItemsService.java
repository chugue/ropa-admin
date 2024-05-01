package com.example.finalproject.domain.items;


import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemsService {
    private final AdminRepository adminRepository;
    private final ItemsRepository itemsRepository;

    // 아이템 저장
    @Transactional
    public void saveItem(ItemsRequest.SaveDTO saveDTO, Admin sessionAdmin) {
        // Admin 정보 조회
        Admin admin = adminRepository.findById(sessionAdmin.getId())
                .orElseThrow(() -> new Exception401("브랜드 관리자의 정보를 찾을 수 없습니다."));

        // DTO를 엔티티로 변환하여 저장
        itemsRepository.save(saveDTO.toEntity(admin));
    }
}
