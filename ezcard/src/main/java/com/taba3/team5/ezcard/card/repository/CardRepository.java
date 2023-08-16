package com.taba3.team5.ezcard.card.repository;

import com.taba3.team5.ezcard.card.entity.CardEntity;
import com.taba3.team5.ezcard.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findOneByCardName(String cardName);
}
