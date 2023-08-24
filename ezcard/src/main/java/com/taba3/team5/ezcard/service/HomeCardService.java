package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.dto.home.HomeCardDto;
import com.taba3.team5.ezcard.entity.card.Card;
import com.taba3.team5.ezcard.entity.user.User;
import com.taba3.team5.ezcard.entity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.taba3.team5.ezcard.service.CardService.cardRepository;

@Service
public class HomeCardService {
    @Autowired
    private UserRepository userRepository;
    private final Map<String, List<Long>> ageToCardIdsMap = new HashMap<>();

    public HomeCardService() {
        // 나이대별로 카드 ID 리스트를 초기화
        ageToCardIdsMap.put("10대", Arrays.asList(9L, 18L, 19L, 20L));
        ageToCardIdsMap.put("20대", Arrays.asList(4L, 10L, 21L, 22L));
        ageToCardIdsMap.put("30대", Arrays.asList(1L, 2L, 3L, 8L));
        ageToCardIdsMap.put("40대", Arrays.asList(5L, 6L, 26L, 27L));
        ageToCardIdsMap.put("50대", Arrays.asList(28L, 29L, 30L, 31L));
        ageToCardIdsMap.put("60대", Arrays.asList(32L, 33L, 34L, 35L));
    }

    // 나이대에 해당하는 카드 ID 리스트를 반환
    private List<Long> getCardIdsForAge(String age) {
        return ageToCardIdsMap.getOrDefault(age, new ArrayList<>());
    }

    // 나이대에 해당하는 카드 정보를 가져와서 HomeCardDto 리스트로 변환하여 반환
    public List<HomeCardDto> homeCard(String age) {
        List<HomeCardDto> homeCardDtoList = new ArrayList<>();

        List<Long> cardIds = getCardIdsForAge(age);

        // 각각의 카드 ID에 대해 HomeCardDto를 생성하고 리스트에 추가
        for (Long cardId : cardIds) {
            // cardId를 이용하여 카드 정보를 가져온다고 가정 (CardRepository를 사용)
            Card card = cardRepository.findById(cardId).orElse(null);
            if (card != null) {
                // 필요한 정보만 추출하여 HomeCardDto 생성
                HomeCardDto homeCardDto = new HomeCardDto();
                homeCardDto.setCardId(card.getCardId());
                homeCardDto.setCardName(card.getCardName());
                homeCardDto.setCardBank(card.getCardBank());
                homeCardDto.setCardSummary1(card.getCardSummary1());
                homeCardDto.setCardSummary2(card.getCardSummary2());
                homeCardDto.setCardSummary3(card.getCardSummary3());
                // 필요한 다른 정보도 추가로 설정

                homeCardDtoList.add(homeCardDto);
            }
        }
        return homeCardDtoList;
    }

    public String findUserAgeByEmail(String email) {
        Optional<User> userOptional = userRepository.findByUserEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getUserAge();
        }
        return null;
    }

    public String findUserNickname(String email) {
        Optional<User> userOptional = userRepository.findByUserEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getUserNickname();
        }
        return null;
    }
}
