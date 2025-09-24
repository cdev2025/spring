package com.mycompany.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 문자열을 직접 리턴
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello Backend!";
    }

    // JSP 뷰로 리턴
    @GetMapping("/hi")
    public String hi(){
        return "hi"; // WEB-INF/views/hi.jsp 리턴
    }
}
