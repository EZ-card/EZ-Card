package com.taba3.team5.ezcard.service;

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

    // 아이디로 회원 정보 찾기
    public MyPageResponseDto findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByUserEmail(email);
        if(optionalUser.isPresent()) {
            return MyPageResponseDto.toMypageUserDto(optionalUser.get());
        } else {
            return null;
        }
    }

}
