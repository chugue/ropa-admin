package com.example.finalproject.domain.love;


import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.codi.CodiRepository;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
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
    public LoveResponse.SaveUserLove saveLove(LoveRequest.Save reqDTO, Integer userId) {
        Optional<Love> loveStatus = loveRepository.findByCodiIdAndUserLoveStatus(reqDTO.getCodiId(), userId);
        Codi codi = codiRepository.findByCodiIdAndUser(reqDTO.getCodiId()).orElseThrow(() ->
                new Exception404("해당 게시물을 찾을 수 없습니다. "));
        if (loveStatus.isPresent()) {
            loveStatus.get().setIsLoved(true);
        }
        Love love = loveRepository.save(Love.builder()
                .user(codi.getUser())
                .codi(codi)
                .isLoved(true).build());

        Long loveCount = loveRepository.countTotalLove(reqDTO.getCodiId());
        return new LoveResponse.SaveUserLove(love, loveCount);
    }
}
