package com.mycompany.mvcproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@ApplicationScope // 애플리케이션 전체에서 단일 인스턴스
public class LoginCountService {

    // 로그인 시도 횟수 / 로그인 성공 횟수 / 서비스 시작 시간
    private final AtomicInteger loginAttemptCount = new AtomicInteger(0);
    private final AtomicInteger successfulLoginCount = new AtomicInteger(0);
    private final LocalDateTime serviceStartTime = LocalDateTime.now();

    // 디버깅용 인스턴스 ID
    private final String instanceId = "LoginCountService-" + System.currentTimeMillis();

    public LoginCountService() {
        System.out.println("LoginCountService 인스턴스 생성: " + instanceId);
    }

    // 로그인 시도 횟수 Count 메서드
    public int incrementAttemptCount(){
        int newCount = loginAttemptCount.incrementAndGet();
        System.out.println("로그인 시도 횟수 증가: " + newCount+ " ( 인스턴스: " + instanceId + " )");
        return newCount;
    }

    // 로그인 성공 횟수 Count 메서드
    public int incrementSuccessCount(){
        int newCount = successfulLoginCount.incrementAndGet();
        System.out.println("성공한 로그인 횟수 증가: " + newCount+ " ( 인스턴스: " + instanceId + " )");
        return newCount;
    }

    // Getters
    public int getAttemptCount() {
        return loginAttemptCount.get();
    }

    public int getSuccessCount() {
        return successfulLoginCount.get();
    }

    public LocalDateTime getServiceStartTime() {
        return serviceStartTime;
    }

    public String getInstanceId() {
        return instanceId;
    }

    // 로그인 성공률 반환 메서드
    public double getSuccessRate(){
        int attempts = getAttemptCount();
        if (attempts==0) return 0.0;
        return (double) getSuccessCount() / attempts * 100;
    }
}
