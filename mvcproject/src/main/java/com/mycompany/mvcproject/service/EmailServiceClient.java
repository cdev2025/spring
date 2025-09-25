package com.mycompany.mvcproject.service;

import com.mycompany.mvcproject.domain.SendEmailRequest;

// 관심사만 분리 : Email 서비스 관련 로직 담당
public class EmailServiceClient {

    // 이메일 발송을 담당하는 메서드
    public void sendEmail(SendEmailRequest request){
        // 실제 이메일 방송 로직을 여기에 작성
        System.out.println("Sending email to : " + request.getToAddress());
        System.out.println("Subject: " + request.getSubject());
        System.out.println("Body: " + request.getBody());
        // 이메일 발송 성공 시 추가적인 로직을 작성 가능
    }
}
