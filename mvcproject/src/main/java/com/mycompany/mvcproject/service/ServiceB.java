package com.mycompany.mvcproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {
    private final ServiceA serviceA; // 필드 주입

    @Autowired
    public ServiceB(@Lazy ServiceA serviceA) { // 생성자 주입
        this.serviceA = serviceA;
    }


    public void methodB(){
        System.out.println("method in ServiceB");
        serviceA.methodA();
    }
}
