package com.taba3.team5.ezcard.entity.wishcard;

import com.taba3.team5.ezcard.entity.card.Card;
import com.taba3.team5.ezcard.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishCardRepository extends JpaRepository<WishCard, Long> {
    List<WishCard> findByUserId(User userId);

    WishCard findByCardIdAndUserId(Card cardId, User userId);
}

