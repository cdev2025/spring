package com.mycompany.mvcproject.controller;

import com.mycompany.mvcproject.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/aop-test", produces = "text/pain;charset=UTF-8")
public class AopTestController {

    private final DemoService demoService;

    @Autowired
    public AopTestController(DemoService demoService) {
        this.demoService = demoService;
    }

    // AOP 테스트 메인 페이지
    @GetMapping("")
    public String testPage(){
        return "aop_test";
    }

    // 기본 작업 테스트
    // http://localhost:8080/aop-test/work
    // http://localhost:8080/aop-test/work?name=Spring
    @GetMapping("/work")
    @ResponseBody
    public String testWork(@RequestParam(defaultValue = "AOP") String name){
        return demoService.doWork(name);
    }

    // 느린 작업 테스트
    // http://localhost:8080/aop-test/slow
    // http://localhost:8080/aop-test/slow?ms=2700
    // http://localhost:8080/aop-test/slow?ms=900
    @GetMapping("/slow")
    @ResponseBody
    public String testSlow(@RequestParam(defaultValue = "1500") Integer ms){
        return demoService.slowTask(ms);
    }

    // 예외 발생 테스트
    // http://localhost:8080/aop-test/error
    // http://localhost:8080/aop-test/error?type=illegal
    // http://localhost:8080/aop-test/error?type=test
    @GetMapping("/error")
    @ResponseBody
    public String testError(@RequestParam(defaultValue = "runtime") String type){
        try{
            return demoService.errorTask(type);
        }catch (Exception e){
            return "예외 발생: " + e.getMessage();
        }

    }

    // 계산 테스트
    // http://localhost:8080/aop-test/calculate"
    // http://localhost:8080/aop-test/calculate?a=30"
    // http://localhost:8080/aop-test/calculate?a=5&b=7"
    @GetMapping("/calculate")
    @ResponseBody
    public String testCalculate(@RequestParam(defaultValue = "10") int a,
                                @RequestParam(defaultValue = "20") int b){
        return demoService.calculateSum(a, b);
    }
}
