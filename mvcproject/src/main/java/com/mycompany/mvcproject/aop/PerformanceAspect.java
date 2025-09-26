package com.mycompany.mvcproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    // @Around : 서비스 메서드 실행 시간 측정
    @Around("execution(* com.mycompany.mvcproject.service.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();
        System.out.println("[PERFORMANCE-START] " + className + "." + methodName + "() 성능 측정 시작");

        Object result = null;
        try {
            // 원본 메서드 실행
            result = joinPoint.proceed();

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("[PERFORMANCE-SUCCESS] " + methodName + "() 성공 완료");
            System.out.println("\t실행 시간 : " + executionTime + "ms");

            // 성능이 1초 이상 소요된다 (성능 경고 메시지)
            if(executionTime > 1000){
                System.out.println("!!!![SLOW-WARNING] " + methodName + "()가 " + executionTime + "ms로 느리게 실행");
            }

            return result;

        }catch (Exception e){
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("[PERFORMANCE-ERROR] " + methodName + "() 예외로 종료");
            System.out.println("\t실행 시간 : " + executionTime + "ms (예외 발생까지)");
            throw e;
        }
    }
}
