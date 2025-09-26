package com.mycompany.mvcproject.racinggame.car;

import com.mycompany.mvcproject.racinggame.engine.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Car {
    private final Map<String, Engine> engines; // <엔진타입(이름), 엔진객체>
    private Engine engine;

    @Autowired  // 생성자 주입방식으로 의존성 주입: 엔진 목록을 주입
    public Car(Map<String, Engine> engines) {
        this.engines = engines; // Map 형태로 여러 엔진을 받아서 저장
    }

    // 사용자가 선택한 엔진 타입에 맞는 엔진을 설정하는 메서드 : url 파라미터로 엔진타입 요청 호출한 메서드
    public void setEngine(String engineType){
        this.engine = engines.get(engineType.toLowerCase() + "Engine");
    }

    public void startCar(){
        System.out.println(engine.start());
    }
}
