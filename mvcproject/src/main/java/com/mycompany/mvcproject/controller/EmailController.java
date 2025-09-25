package com.mycompany.mvcproject.controller;

import com.mycompany.mvcproject.domain.SendEmailRequest;
import com.mycompany.mvcproject.service.EmailServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/email")
public class EmailController {
    
    // http://localhost:8080/email/sendEmail
    @RequestMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(){
        // 이메일을 보내는 서비스 클라이언트 객체 생성
        EmailServiceClient emailServiceClient = new EmailServiceClient();
        // 이메일 내용 작성
        SendEmailRequest sendEmailRequest = generateEmailRequest();

        // 이메일 보내기
        emailServiceClient.sendEmail(sendEmailRequest);

        return "Success";
    }

    // 간단한 이메일 요청 객체 생성하는 test용 메서드
    public SendEmailRequest generateEmailRequest(){
        SendEmailRequest request = new SendEmailRequest();
        request.setToAddress("test@example.com");
        request.setSubject("Test Email");
        request.setBody("This is a test email.");
        return request;
    }
}
