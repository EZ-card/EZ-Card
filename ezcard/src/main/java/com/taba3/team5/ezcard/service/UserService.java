package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.dto.mypage.MyPageResponseDto;
import com.taba3.team5.ezcard.dto.user.LoginRequestDto;
import com.taba3.team5.ezcard.dto.user.LoginUserDto;
import com.taba3.team5.ezcard.entity.user.User;
import com.taba3.team5.ezcard.entity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. 회원가입
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    // 2. 로그인
    public LoginUserDto login(LoginRequestDto loginRequestDto) {

        Optional<User> findByUserEmail = userRepository.findByUserEmail(loginRequestDto.getEmail());
        if(findByUserEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            User user = findByUserEmail.get();
            if(user.getUserPwd().equals(loginRequestDto.getPassword())) {
                // 비밀번호 일치 시
                LoginUserDto authDto = LoginUserDto.toUserDto(user);
                return authDto;
            } else {
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            return null;
        }
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
