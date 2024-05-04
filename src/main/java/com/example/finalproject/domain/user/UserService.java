package com.example.finalproject.domain.user;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.utils.AppJwtUtill;
import com.example.finalproject.domain.codiItems.CodiItems;
import com.example.finalproject.domain.codiItems.CodiItemsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CodiItemsRepository codiItemsRepository;

    //회원가입
    @Transactional
    public UserResponse.JoinDTO join(UserRequest.JoinDTO reqDTO) {
        User user = userRepository.save(User.builder()
                .email(reqDTO.getEmail())
                .password(reqDTO.getPassword())
                .nickName(reqDTO.getNickName())
                .status("신청전")
                .createdAt(reqDTO.getCreatedAt())
                .blueChecked(false)
                .build());
        return new UserResponse.JoinDTO(user);
    }

    //JWT 돌려주기
    public String login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));

        String jwt = AppJwtUtill.create(user);
        AppJwtUtill.verify(jwt);

        return jwt;
    }

    // 앱 사용자 로그인
    public UserResponse.LoginDTO loginByDTO(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.LoginDTO(user);
    }

    // 앱 세팅 페이지
    public UserResponse.SettingPageDTO settingPage(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.SettingPageDTO(user);
    }

    // 앱 사용자 프로필 페이지
    public UserResponse.ProfilePageDTO profilePage(SessionUser sessionUser) {
        User user = userRepository.findByUserIdWithPhoto(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.ProfilePageDTO(user, new UserResponse.ProfilePageDTO.PhotoDTO(user.getPhoto()));
    }

    // 앱 사용자 크리에이터 지원 페이지
    public UserResponse.CreatorApplyDTO creatorApplyPage(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return new UserResponse.CreatorApplyDTO(user);
    }

    // 앱 사용자 크리에이터 지원
    @Transactional
    public UserResponse.CreatorApplyDTO creatorApply(UserRequest.CreatorApplyDTO creatorApplyDTO, SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));

        user.setHeight(creatorApplyDTO.getHeight());
        user.setWeight(creatorApplyDTO.getWeight());
        user.setInstagram(creatorApplyDTO.getInstagram());
        user.setJob(creatorApplyDTO.getJob());
        user.setStatus("승인 대기");

        userRepository.save(user);

        return new UserResponse.CreatorApplyDTO(user);
    }

    //크리에이터 뷰 페이지
    public UserResponse.CreatorViewDTO creatorView(SessionUser sessionUser, Integer id) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));


        // 2. 선택된 크리에이터의 정보와 관련된 코디 및 아이템을 가져오기
        List<CodiItems> codiItemsList = codiItemsRepository.findByUserWithCodiLIstItemsList(id);

        // 3. DTO로 매핑하기
        List<UserResponse.CreatorViewDTO> respDTO = codiItemsList.stream().map(codiItems -> {
            return new UserResponse.CreatorViewDTO(user, codiItemsList, ite);
        }).toList();

        return respDTO;
    }

}
