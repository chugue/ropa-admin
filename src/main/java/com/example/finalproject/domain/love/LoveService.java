package com.example.finalproject.domain.love;


import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.codi.CodiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoveService {
    private final LoveRepository loveRepository;
    private final CodiRepository codiRepository;

    @Transactional
    public LoveResponse.SaveUserLove saveLove(Integer codiId, Integer userId) {
        Optional<Love> loveStatus = loveRepository.findByCodiIdAndUserLoveStatus(codiId, userId);
        Codi codi = codiRepository.findByCodiIdAndUser(codiId).orElseThrow(() ->
                new Exception404("해당 게시물을 찾을 수 없습니다."));
        Love love;
        if (loveStatus.isEmpty()) {
            love = loveRepository.save(Love.builder()
                    .user(codi.getUser())
                    .codi(codi)
                    .isLoved(true).build());
        } else {
            love = loveStatus.get();
            love.setIsLoved(true);
        }

        Long loveCount = loveRepository.countTotalLove(codiId);
        return new LoveResponse.SaveUserLove(love, loveCount);
    }

    // 좋아요 취소
    @Transactional
    public LoveResponse.DeleteInfo deleteLove(Integer codiId, Integer userId) {
        Codi codi = codiRepository.findByCodiIdAndUser(codiId).orElseThrow(() ->
                new Exception404("해당 게시물을 찾을 수 없습니다."));
        Optional<Love> loveOP = loveRepository.findByCodiIdAndUserLoveStatus(codi.getId(), userId);

        if (loveOP.isPresent()) {
            loveRepository.delete(loveOP.get());
        }
        Long loveCount = loveRepository.countTotalLove(codiId);
        return new LoveResponse.DeleteInfo(codiId, userId, loveCount);
    }
}
