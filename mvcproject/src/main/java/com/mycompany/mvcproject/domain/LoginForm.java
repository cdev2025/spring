package com.mycompany.mvcproject.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope // 요청마다 새로운 인스턴스 생성
public class LoginForm {
    private String email;
    private String password;

    // 확인용 인스턴스 ID
    private final String instanceId = "LoginForm-" + System.currentTimeMillis();

    // 간단한 인증 로직
    public boolean authenticate(){
        System.out.println("인증 처리 중... 인스턴스 ID: " + instanceId);
        return "test@test.com".equals(email) && "1234!".equals(password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
