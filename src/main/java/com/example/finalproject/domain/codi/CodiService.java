package com.example.finalproject.domain.codi;

import com.example.finalproject.domain.codiItems.CodiItems;
import com.example.finalproject.domain.codiItems.CodiItemsRepository;
import com.example.finalproject.domain.photo.Photo;
import com.example.finalproject.domain.photo.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CodiService {
    private final CodiRepository codiRepository;
    private final CodiItemsRepository codiItemsRepository;
    private final PhotoRepository photoRepository;

    // 코디 보기 페이지 요청 - 페이지 내 아이템 목록, 크리에이터 코디목록 포함
    public void codiPage(Integer codiId) {
        // codiItems로 조회해서 Codi 정보랑 연계된 Items가져오기
        List<CodiItems> codiItemsList = codiItemsRepository.findByCodiWithItems(codiId);
        // codiId로 코디 메인 사진 조회
        List<Photo> codiMainPhotos = photoRepository.findByCodiId(codiId);
        // CreatorId로 모든 코디를 조회해서 메인 사진 가져오기
//        photoRepository.findByUserIdWithCodiesAndPhoto();



    }
}
