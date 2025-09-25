package com.mycompany.mvcproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
    private final ServiceB serviceB; // 필드 주입

    @Autowired
    public ServiceA(@Lazy ServiceB serviceB) { // 생성자 주입
        this.serviceB = serviceB;
    }

    public  void methodA(){
        System.out.println("method in ServiceA");
        serviceB.methodB();
    }
}
