package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    // 앱] 메인 홈 화면 요청
    public PhotoResponse.HomeDTO getHomeLists() {
        // 인기 크리에이터 사진 조회 (마일리지를 많이 받은 순대로 나열)
        List<Photo> popularCreatorsList = photoRepository.findByMileageWithPhoto();
        // 인기 아이템의 id 조회 (총 판매량 순으로 아이템을 나열)
        List<Integer> itemsId = orderHistoryRepository.findItemsIdByTotalSales();
        // 인기 아이템으로 얻어진 id로 사진 가져오기
        List<Photo> popularItemsList = photoRepository.findByItemsIds(itemsId);

        return new PhotoResponse.HomeDTO(popularCreatorsList, popularItemsList);
    }
}
