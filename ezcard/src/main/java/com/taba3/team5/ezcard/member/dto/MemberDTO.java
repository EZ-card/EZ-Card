package com.taba3.team5.ezcard.member.dto;

import com.taba3.team5.ezcard.member.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberNickName;
    private int memberAge;
    private MemberEntity.Gender memberGender;
    private MemberEntity.Job memberJob;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberNickName(memberEntity.getMemberNickName());
        memberDTO.setMemberAge(memberEntity.getMemberAge());
        memberDTO.setMemberGender(memberEntity.getMemberGender());
        memberDTO.setMemberJob(memberEntity.getMemberJob());
        return memberDTO;
    }
}
