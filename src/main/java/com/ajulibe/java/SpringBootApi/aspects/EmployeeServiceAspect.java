package com.ajulibe.java.SpringBootApi.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(1)
public class EmployeeServiceAspect {

    @Pointcut("execution(* com.ajulibe.java.SpringBootApi.service.EmployeeService.*(..))")
    private void beforeanyService() {
    }

    @Before("beforeanyService() && args(empId, fname, sname)")
    public void beforeAdvice(JoinPoint joinPoint, String empId, String fname, String sname) {
        System.out.println("Before method:" + joinPoint.getSignature());
        System.out.println("Creating Employee with first name - " + fname + ", second name - " + sname + " and id - " + empId);
    }
}