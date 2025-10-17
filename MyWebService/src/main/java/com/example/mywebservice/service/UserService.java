package com.example.mywebservice.service;

import com.example.mywebservice.dto.AddUserDto;
import com.example.mywebservice.entity.User;
import com.example.mywebservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 사용자 관련 비즈니스 로직 처리 서비스
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Long save(AddUserDto dto){
        log.info("▶️ 새로운 사용자 등록: {} ({})", dto.getName(), dto.getEmail());

        // 이메일 중복 체크
        if( userRepository.existsByEmail(dto.getEmail()) ){
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + dto.getEmail());
        }

        // 비밀번호 암호화 해서 사용자 생성
        User user = User.builder()
                .email(dto.getEmail())
                .password( passwordEncoder.encode(dto.getPassword()) )
                .name(dto.getName())
                .build();

        User savedUser = userRepository.save(user);
        log.info("▶️ 사용자 등록 완료: ID={}, Email={}", savedUser.getId(), savedUser.getEmail());

        return savedUser.getId();
    }
}
