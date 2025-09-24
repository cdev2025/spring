package com.mycompany.mvcproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("msg", "Spring");
        return "hello"; // -> /WEB-INF/views/hello.jsp
    }

    //@RequestMapping("/requestMappingGetTest")
    @RequestMapping(value="/requestMappingGetTest", method = RequestMethod.GET)
    public String requestMappingGetTest(Model model){
        model.addAttribute("textFromController", "requestMappingGetTest");
        return "requestHello"; // /WEB-INF/views/requestHello.jsp
    }

    //[오류] "/requestParamTest"
    //"/requestParamTest?b=anyString" => a=0 / b=anyString / c=true
    //"/requestParamTest?a=1&b=anyString&c=false"
    @RequestMapping("/requestParamTest")
    public String requestParamTest(
            // 파라미터 이름 "a"인 파라미터. 필수 요소 아님., 정달이 안될경우 기본값 "0"
            @RequestParam(name = "a", required = false, defaultValue = "0") int a,
            @RequestParam("b") String b, // 지정이 안되어 있으면 필수로 받아야함.
            @RequestParam(name="c", defaultValue="true") boolean c //"값이 전달이 전달이 안될 경우 기본값 "true"
            ){
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        return "hello";
    }

    // http://localhost:8080/requestParamMapTest?param1=value1&param2=value2&param3=value3
    @RequestMapping("/requestParamMapTest")
    public String requestParamMapTest(
            @RequestParam Map<String, String> map
            ){
        for(Map.Entry<String, String> entry: map.entrySet()){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        return "hello";
    }

    //[오류 404] http://localhost:8080/pathVariableTest
    //[오류 400] http://localhost:8080/pathVariableTest/spring/test/complete
    //http://localhost:8080/pathVariableTest/spring/test/2024
    @RequestMapping("/pathVariableTest/{a}/{b}/{c}")
    public String pathVariableTest(
            @PathVariable(value="a") String a,
            @PathVariable String b,
            @PathVariable int c
    ){
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        return "hello";
    }
}
