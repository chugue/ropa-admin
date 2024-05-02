package com.example.finalproject.domain.items;


import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import com.example.finalproject.domain.category.Category;
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

    // 아이템 수정
    @Transactional
    public void updateItem(Integer itemId, ItemsRequest.UpdateDTO updateDTO, Admin sessionAdmin) {
        // Admin 정보 조회
        Admin admin = adminRepository.findById(sessionAdmin.getId())
                .orElseThrow(() -> new Exception401("브랜드 관리자의 정보를 찾을 수 없습니다."));

        // 아이템 정보 조회
        Items items = itemsRepository.findById(itemId)
                .orElseThrow(() -> new Exception404("아이템을 찾을 수 없습니다."));

        // 아이템 정보 업데이트
        items.setName(updateDTO.getName());
        items.setDescription(updateDTO.getDescription());
        items.setSize(updateDTO.getSize());
        items.setPrice(updateDTO.getPrice());
        items.setDiscountPrice(updateDTO.getDiscountPrice());
        items.setStock(updateDTO.getStock());

        // 카테고리 정보 업데이트
        Category category = items.getCategory();
        category.setMain(updateDTO.getMainCategory());
        category.setSub(updateDTO.getSubCategory());

        // 엔티티 저장
        itemsRepository.save(items);
    }

}
