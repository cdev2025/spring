package com.mycompany.mvcproject.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    // 포인트컷 정의 : service 패키지의 모든 메서드
    @Pointcut("execution(* com.mycompany.mvcproject.service.*.*(..))")
    public void serviceMethods() {}

    // 컨트롤러 메서드 포인트컷
    @Pointcut("execution(* com.mycompany.mvcproject.controller.*.*(..))")
    public void controllerMethods() {}

    // @Before: 서비스 메서드 실행 전 로깅
    @Before("serviceMethods()")
    public void logServiceBefore(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("\n[AOP-SERVICE-BEFORE] " + className + "." + methodName + "() 호출");
        if(args.length>0){
            System.out.println("\t-전달된 파라미터: " + Arrays.toString(args));
        }
    }

    // @Before: 컨트롤러 메서드 호출 로깅
    @Before("controllerMethods()")
    public void logControllerBefore(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("\n[AOP-CONTROLLER-BEFORE] " + methodName  + "() 웹 요청 처리 시작");
    }

    // @AfterReturning : 정상 반환 시 로깅


    // @AfterThrowing : 예외 발생 시 로깅
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[AOP-ERROR] " + methodName + "() 예외 발생!");
        System.out.println("\t- 예외 타입 : "+ exception.getClass().getSimpleName());
        System.out.println("\t- 예외 메시지 : " + exception.getMessage());
    }


    // @After : 메서드 완료 후 항상 실행
}
