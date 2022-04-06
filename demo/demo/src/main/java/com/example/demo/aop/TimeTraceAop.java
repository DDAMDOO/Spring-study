package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* com.example.demo..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("start: " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally{
            long finish = System.currentTimeMillis();
            long time = finish-start;
            System.out.println("end : " + joinPoint.toString() + " " + time + "ms");
        }

    }

}
