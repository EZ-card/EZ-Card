package com.taba3.team5.ezcard.entity.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    Optional<Card> findOneByCardName(String cardName);

    Optional<Card> findOneByCardId(Long cardId);

}
