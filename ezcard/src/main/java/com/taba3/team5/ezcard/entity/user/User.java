package com.taba3.team5.ezcard.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_age")
    private int userAge;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_job")
    private String userJob;

    public User() {

    }

    // Getters and setters
}

