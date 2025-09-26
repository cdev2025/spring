package com.mycompany.mvcproject.service;

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


    /**
     * 의도적으로 지연 시간을 발생하는 메서드
     *
     * 메서드 성능 측정 AOP 동작 확인용
     * Thread.sleep()_을 사용해서 실제 시간이 오래 걸리는 작업 시뮬레이션
     *
     * @param ms : 지연시간(밀리처). null이면 기본값 1000ms 사용
     * @return : 지연 작업 완료 메시지
     */

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

    /**
     * 간단한 계산을 수행하는 메서드
     *
     * 파라미터가 있는 메서드에서 AOP가 어떻게 파라미터를 로깅하는지 확인하기 위한 메서드
     *
     * @param a : 첫번째 숫자
     * @param b : 두번째 숫자
     * @return 계산 결과 메시지
     */
}
