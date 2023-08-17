package com.taba3.team5.ezcard.repository;

import com.taba3.team5.ezcard.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findOneByCardName(String cardName);
}
