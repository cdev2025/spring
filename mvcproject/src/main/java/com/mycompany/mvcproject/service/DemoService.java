package com.mycompany.mvcproject.service;

import com.mycompany.mvcproject.aop.annotation.Monitored;
import org.springframework.stereotype.Service;

/*
* AOP 시나리오 테스트 목적 서비스
* - 정상적인 비즈니스 로직 실행 (기본 AOP 확인용)
* - 시간이 오래 걸리는 작업(성능 측정 AOP 확인용)
* - 예외가 발생하는 상황 (예외 처리 AOP 확인용)
* */
@Service
public class DemoService {

    /**
     * 기본 작업 수행하는 메서드
     *
     * @param name : 작업 대상 이름 (null 일 경우 "익명"으로 처리)
     * @return : 작업 완료 메시지
     */
    public String doWork(String name){
        // null 처리
        String targetName = (name==null) ? "익명" : name;

        System.out.println("[비즈니스 로직] 작업 수행 중: " +  targetName);

        return "작업 완료: " + targetName;
    }

    /**
     * 의도적으로 지연 시간을 발생하는 메서드
     *
     * 메서드 성능 측정 AOP 동작 확인용
     * Thread.sleep()_을 사용해서 실제 시간이 오래 걸리는 작업 시뮬레이션
     *
     * @param ms : 지연시간(밀리처). null이면 기본값 1000ms 사용
     * @return : 지연 작업 완료 메시지
     */
    public String slowTask(Integer ms){
        int sleepTime = (ms==null) ? 1000 : ms;

        try {
            System.out.println("[비즈니스 로직] " + sleepTime + "ms 지연 작업 시작");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 스레드 인터럽트 발생 시 인터럽트 상태 복원 (권장 패턴)
            System.out.println("[비즈니스 로직] 작업이 중단되었습니다.");
        }

        return "지연 작업 완료: " +sleepTime + "ms";
    }

    /**
     * 다양한 예외를 발생시키는 메서드
     *
     * 예외 처리 AOP 동작 확인용
     *
     * @param type : 예외 타입 지정 ("runtime" , "illegal", 기타는 정상 처리)
     * @return 정상 처리 시 완료 메시지
     * @throws RuntimeException : type이 "runtime"인 경우
     * @throws IllegalArgumentException : type이 "illegal"인 경우
     */
    public String errorTask(String type){
        System.out.println("[비즈니스 로직] 예외 발생 시뮬레이션: " + type);

        switch(type){
            case "runtime":
                // 런타임 예외
                throw new RuntimeException("런타임 예외 발생!");
            case "illegal":
                // 잘못된 인자(파라미터)
                throw new IllegalArgumentException("잘못된 인자 예외!");
            default:
                System.out.println("[비즈니스 로직] 정상적인 처리가 완료되었습니다.");
                return "정상 처리 완료";
        }
    }

    /**
     * 간단한 계산을 수행하는 메서드
     *
     * 파라미터가 있는 메서드에서 AOP가 어떻게 파라미터를 로깅하는지 확인하기 위한 메서드
     *
     * @param a : 첫번째 숫자
     * @param b : 두번째 숫자
     * @return 계산 결과 메시지
     */
    public String calculateSum(int a, int b){
        System.out.println("[비즈니스 로직] 계산 수행: " + a + " + " + b);

        int result = a+b;
        return "계산 결과: " + a + " + " + b + " = " + result;
    }

    @Monitored("중요한 비즈니스 계산 로직")
    public String importantCalculation(int x, int y, String operation){
        System.out.println("[비즈니스 로직] 중요한 계산 수행: " + x + " " + operation + " " + y);

        try{
            Thread.sleep(300); // 0.3초 지연
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        int result;
        switch (operation){
            case "+": result = x+y; break;
            case "-": result = x-y; break;
            case "*": result = x*y; break;
            case "/":
                if(y==0) throw new IllegalArgumentException("0으로 나눌 수 없습니다!!!");
                result = x/y;
                break;
            default: throw new IllegalArgumentException("지원하지 않는 연산: " + operation);
        }

        return "중요 계산 결과: " + x + " " + operation + " " + y + " = " + result;
    }
}
