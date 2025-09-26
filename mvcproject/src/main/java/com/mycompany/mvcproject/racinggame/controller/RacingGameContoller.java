package com.mycompany.mvcproject.racinggame.controller;

import com.mycompany.mvcproject.racinggame.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RacingGameContoller {
    private final Car car;

    @Autowired
    public RacingGameContoller(Car car) {
        this.car = car;
    }

    // http://localhost:8080/race
//    @GetMapping("/race")
//    @ResponseBody
//    public String startRace(){
//        car.startCar();
//        return "Race started!";
//    }

    // http://localhost:8080/race
    // http://localhost:8080/race?engine=V6
    // http://localhost:8080/race?engine=Electric
    @GetMapping("/race")
    @ResponseBody
    public String startRace(@RequestParam(defaultValue="V6") String engine){ // url 파라미터로 engine 문자열 받아 옴(기본값: v6)
        car.setEngine(engine);   // 파라미터 값으로 엔진 초기화 추가
        car.startCar();
        return "Race started!";
    }

}
