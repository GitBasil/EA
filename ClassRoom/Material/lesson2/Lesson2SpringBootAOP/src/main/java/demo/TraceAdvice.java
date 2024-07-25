package demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TraceAdvice {
    @Before("execution(* demo.AccountService.*(..))")
    public void tracebeforemethod(JoinPoint joinpoint) {
        System.out.println("before execution of method "+joinpoint.getSignature().getName());
    }

    @After("execution(* demo.AccountService.*(..))")
    public void traceaftermethod(JoinPoint joinpoint) {
        System.out.println("after execution of method "+joinpoint.getSignature().getName());
    }
}
