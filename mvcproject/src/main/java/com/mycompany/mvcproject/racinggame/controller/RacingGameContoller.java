package com.mycompany.mvcproject.racinggame.controller;

import com.mycompany.mvcproject.racinggame.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RacingGameContoller {
    private final Car car;

    @Autowired
    public RacingGameContoller(Car car) {
        this.car = car;
    }

    // http://localhost:8080/race
    @GetMapping("/race")
    @ResponseBody
    public String startRace(){
        car.startCar();
        return "Race started!";
    }
}
