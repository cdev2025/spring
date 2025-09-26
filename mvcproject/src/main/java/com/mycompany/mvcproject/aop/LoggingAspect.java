package com.mycompany.mvcproject.aop;

import org.aspectj.lang.JoinPoint;
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
            System.out.println("---전달된 파라미터: " + Arrays.toString(args));
        }
    }
}
