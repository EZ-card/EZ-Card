package com.taba3.team5.ezcard.member.repository;

import com.taba3.team5.ezcard.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
