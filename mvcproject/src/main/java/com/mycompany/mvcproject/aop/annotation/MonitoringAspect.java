package com.mycompany.mvcproject.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MonitoringAspect {

    @Around("@annotation(monitored))")
    public Object monitor(ProceedingJoinPoint joinPoint, Monitored monitored) throws Throwable{
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String description = monitored.value();
        boolean includeParams = monitored.includeParams();

        System.out.println("\n[MONITORING-START] " + className + "." + methodName + "() 모니터링 시작");
        if(!description.isEmpty()){
            System.out.println("\t설명: " + description);
        }

        if(includeParams && joinPoint.getArgs().length>0 ){
            System.out.println("\t파라미터: " + Arrays.toString(joinPoint.getArgs()));
        }

        long startTime = System.nanoTime();
        try{
            Object result = joinPoint.proceed();

            long endTime = System.nanoTime();
            double executionTimeMs = (endTime-startTime) / 1_000_000.0;

            System.out.println("[MONITORING-SUCCESS] " + methodName + "() 모니터링 완료");
            System.out.println("\t정밀 실행시간: " + String.format("%.3f", executionTimeMs) + "ms");
            System.out.println("\t결과: " + result);
            System.out.println("----------------------------------------------------");

            return result;
        }catch(Exception e){
            long endTime = System.nanoTime();
            double executionTimeMs = (endTime-startTime) / 1_000_000.0;

            System.out.println("[MONITORING-ERROR] " + methodName + "() 모니터링 - 예외 발생");
            System.out.println("\t실행시간: " + String.format("%.3f", executionTimeMs) + "ms");
            System.out.println("\t예외: " + e.getMessage());
            System.out.println("----------------------------------------------------");

            throw e;
        }

    }
}
