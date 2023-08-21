package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.dto.mypage.MyPageRequestDto;
import com.taba3.team5.ezcard.dto.mypage.MyPageResponseDto;
import com.taba3.team5.ezcard.entity.user.User;
import com.taba3.team5.ezcard.entity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyPageService {
    private final UserRepository userRepository;

    @Autowired
    public MyPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 이메일로 회원 정보 찾기
    public MyPageResponseDto findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByUserEmail(email);
        if(optionalUser.isPresent()) {
            return MyPageResponseDto.toMypageUserDto(optionalUser.get());
        } else {
            return null;
        }
    }

    // userId로 회원 정보 업데이트
    public boolean updateUserInformation(Long loginId, MyPageRequestDto myPageRequestDto) {
        Optional<User> optionalUser = userRepository.findById(loginId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserPwd(myPageRequestDto.getPassword());
            user.setUserNickname(myPageRequestDto.getNickname());
            user.setUserAge(myPageRequestDto.getAge());
            user.setUserGender(myPageRequestDto.getGender());
            user.setUserJob(myPageRequestDto.getJob());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
