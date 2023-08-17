package com.taba3.team5.ezcard.entity;

import com.taba3.team5.ezcard.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(nullable = false, unique = true) // unique 제약조건 추가
    private String memberEmail;

    @Column(nullable = false)
    private String memberPassword;

    @Column(nullable = false)
    private String memberNickName;

    @Column(nullable = false)
    private int memberAge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender memberGender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Job memberJob;

    public enum Gender {
        M, F
    }

    public enum Job {
        STUDENT, WORKER
    }
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberNickName(memberDTO.getMemberNickName());
        memberEntity.setMemberAge(memberDTO.getMemberAge());
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberJob(memberDTO.getMemberJob());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberNickName(memberDTO.getMemberNickName());
        memberEntity.setMemberAge(memberDTO.getMemberAge());
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberJob(memberDTO.getMemberJob());
        return memberEntity;
    }

}
