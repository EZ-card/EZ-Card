package com.taba3.team5.ezcard.entity.cardbenefit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardBenefitRepository extends JpaRepository<CardBenefit,Long> {

    List<CardBenefit> findAllByCard_CardId(Long cardId);
}
