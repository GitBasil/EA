package PersonPet.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class PersonAdvice {
    
    @Around("execution(* PersonPet.Services.*.*(..))")
     public Object aroundTrace(ProceedingJoinPoint joinpoint) throws Throwable {
        StopWatch sw = new StopWatch(); 
        sw.start(joinpoint.getSignature().getName()); 

        Object retVal = joinpoint.proceed(); 
        
        sw.stop();

        if(sw.getTotalTimeMillis() > 0)
        System.out.println(joinpoint.getSignature().getDeclaringTypeName() + "/ " + joinpoint.getSignature().getName() + " Time =" + sw.getTotalTimeMillis() + " ms");
        return retVal;
     }

}
