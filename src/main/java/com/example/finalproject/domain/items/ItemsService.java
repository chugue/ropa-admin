package com.example.finalproject.domain.items;


import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemsService {
    private final AdminRepository adminRepository;
    private final ItemsRepository itemsRepository;

    // 아이템 저장
    @Transactional
    public void saveItem(ItemsRequest.SaveDTO saveDTO, Admin sessionBrand) {
        // Admin 정보 조회
        Admin admin = adminRepository.findById(sessionBrand.getId())
                .orElseThrow(() -> new Exception401("브랜드 관리자의 정보를 찾을 수 없습니다."));

        itemsRepository.save(saveDTO.toEntity(admin));
    }

    // 아이템 목록
    public List<ItemsResponse.listDTO> findItemsByAdminId(Admin sessionBrand) {
        // Admin 정보 조회
        Admin admin = adminRepository.findById(sessionBrand.getId())
                .orElseThrow(() -> new Exception401("브랜드 관리자의 정보를 찾을 수 없습니다."));

        List<Items> item = itemsRepository.findItemsByAdminId(sessionBrand.getId());
        return item.stream().map(ItemsResponse.listDTO::new).toList();
    }

    // 아이템 상세보기
    public ItemsResponse.DetailDTO findItemsByAdminIdAndItemId(Admin sessionAdmin, int itemId) {
        // Admin 정보 조회
        Admin admin = adminRepository.findById(sessionAdmin.getId())
                .orElseThrow(() -> new Exception401("브랜드 관리자의 정보를 찾을 수 없습니다."));

        // 아이템 정보 조회
        Items items = itemsRepository.findItemsByAdminIdAndItemId(admin.getId(), itemId)
                .orElseThrow(() -> new Exception404("브랜드 아이템 정보를 찾을 수 없습니다."));
        return new ItemsResponse.DetailDTO(items);
    }
}
