package com.taba3.team5.ezcard.entity.agecard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgeCardRepository extends JpaRepository<AgeCard,Long> {
    List<AgeCard> findByMatchAge(String matchAge);
}
