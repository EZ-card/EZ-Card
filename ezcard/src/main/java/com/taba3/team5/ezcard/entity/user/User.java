package com.taba3.team5.ezcard.entity.user;

import com.taba3.team5.ezcard.dto.user.JoinRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_age")
    private String userAge;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_job")
    private String userJob;

    public static User toUser(JoinRequestDto joinRequestDto) {
        User user = new User();
        user.setUserEmail(joinRequestDto.getEmail());
        user.setUserPwd(joinRequestDto.getPassword());
        user.setUserNickname(joinRequestDto.getNickname());
        user.setUserAge(joinRequestDto.getAge());
        user.setUserGender(joinRequestDto.getGender());
        user.setUserJob(joinRequestDto.getJob());
        return user;
    }

    // Getters and setters
}

