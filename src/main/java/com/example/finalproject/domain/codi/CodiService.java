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
    public CodiResponse.MainViewDTO codiPage(Integer codiId) {

        // codiId로 코디 메인 사진들 조회
        List<Photo> mainCodiPhotos = photoRepository.findByCodiId(codiId);

        // codiItems로 조회해서 Codi 정보랑 연계된 Items가져오기
        List<CodiItems> codiItemsList = codiItemsRepository.findByCodiWithItems(codiId);
        List<Integer> itemsIdList = codiItemsList.stream().map(codiItems -> codiItems.getItems().getId()).toList();

        itemsIdList.forEach(integer -> System.out.println("아이템 번호 : " + integer));

        List<Photo> codiItemPhotos = photoRepository.findByItemsIds(itemsIdList);

        // CreatorId로 모든 코디를 조회해서 여러 코디 메인 사진 가져오기
        Codi selectedCodi = codiItemsList.getFirst().getCodi();
        List<Photo> otherCodiPhotos = photoRepository.findByUserIdWithCodiesAndPhoto(selectedCodi.getUser().getId());

        return new CodiResponse.MainViewDTO(
                selectedCodi, mainCodiPhotos, codiItemPhotos, otherCodiPhotos);

    }
}
