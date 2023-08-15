package com.taba3.team5.ezcard.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mbr_seq")
    private Long id;

    @Column(name = "mbr_email", nullable = false, unique = true)
    private String email;

    @Column(name = "mbr_pwd", nullable = false)
    private String password;

    @Column(name = "mbr_name", nullable = false)
    private String nickname;

    @Column(name = "mbr_age", nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "mbr_gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "mbr_job", nullable = false)
    private Job job;

    public enum Gender {
        M, F
    }

    public enum Job {
        STUDENT, WORKER
    }
}
