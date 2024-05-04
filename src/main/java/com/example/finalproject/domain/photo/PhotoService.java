package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.love.LoveRepository;
import com.example.finalproject.domain.love.LoveResponse;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final LoveRepository loveRepository;

    // 앱] 메인 홈 화면 요청
    public PhotoResponse.HomeDTO getHomeLists() {
        // 코디의 좋아요의 합으로 인기크리에이터를 좋아요받은 순으로 나열 + 대표 사진까지 찾기
        List<LoveResponse.UserLoveCount> userLoveCounts = loveRepository.findUserIdsSortedByLoveCount();
        List<Integer> popularCreators = userLoveCounts.stream().map(userLoveCount -> userLoveCount.getUserId()).toList();
        List<Photo> popularUserPhotos = photoRepository.findByUserId(popularCreators);

        // 인기 아이템의 id 조회 (총 판매량 순으로 아이템을 나열) + 사진 가져오기
        List<Integer> itemsId = orderHistoryRepository.findItemsIdByTotalSales();
        List<Photo> popularItemsPhotos = photoRepository.findByItemsIds(itemsId);

        // 인기 코디 좋아요 순으로 정렬
        List<LoveResponse.CodiLoveCount> popularCodies = loveRepository.findCodiIdsSortedByLoveCount();
        List<Integer> popularCodiIdes = popularCodies.stream().map(codiLoveCount -> codiLoveCount.getCodiId()).toList();
        List<Photo> popularCodiPhotos = photoRepository.findByCodiIds(popularCodiIdes);

        return new PhotoResponse.HomeDTO(popularUserPhotos, popularItemsPhotos, popularCodiPhotos);
    }
}
