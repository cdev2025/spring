package com.mycompany.mvcproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;

@Service
@SessionScope // 세션 동안 상태 유지
public class LoggedUserManagementService {
    private String userName;
    private String userEmail;
    private LocalDateTime longinTime;

    // 디버깅을 위한 세션 ID
    private final String sessionId = "Session - " + System.currentTimeMillis();

    public void login(String userName, String userEmail){
        this.userName = userName;
        this.userEmail = userEmail;
        this.longinTime = LocalDateTime.now();
        System.out.println("사용자 로그인: " + userName + " ( 세션 ID : " + sessionId + " )");
    }

    public void logout(){
        System.out.println("사용자 로그아웃: " + userName + " ( 세션 ID: " + sessionId + " )");
        this.userName = null;
        this.userEmail = null;
        this.longinTime = null;
    }

    // Getters

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LocalDateTime getLonginTime() {
        return longinTime;
    }

    public boolean isLoggedIn() { return userName != null; }

    public String getSessionId() {
        return sessionId;
    }
}
