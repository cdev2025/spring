package com.example.board.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

// JPA Auditing 설정
@Configuration
@EnableJpaAuditing // JPA Auditing 기능 활성화 => @EntityListeners(AuditingEntityListener.class) 적용된 엔티티들을 모니터링
public class JpaConfig {

    /**
     * AuditorAware 빈 등록
     *
     * AuditorAware<T> : 현재 사용자(Auditor)를 제공하는 인터페이스
     * - T: 사용자 나타내는 타입 (String, Long, User 객체 등)
     * -
     *
     * 실무에서는 Spring Security의 SecurityContext에서 사용자 정보를 가져옴
     * 오늘은 학습 목적으로 "system"이라는 고정 값 반환
     * **/
    @Bean
    public AuditorAware<String> auditorProvider(){
        return () ->{
            // 실무 환경에서는
//            Authentication authentication = SpringContextHolder.getContext().getAuthentication();
//            if(authentication != null && authentication.isAuthenticated()) {
//                return Optional.of(authentication.getName());
//            }

            // 현재 학습용으로 고정값 반환
            return Optional.of("system");
        };
    }
}
