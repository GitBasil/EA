package customers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class DAOAdvice {
    @Around("execution(* customers.ICustomerDAO.*(..))")
    public Object aroundmethod(ProceedingJoinPoint joinpoint) throws Throwable {

        StopWatch sw = new StopWatch(); 
        sw.start(joinpoint.getSignature().getName()); 
        Object retVal = joinpoint.proceed(); 
        sw.stop();

        System.out.println(joinpoint.getSignature().getName() + " Time =" + sw.getTotalTimeMillis());

        return retVal;
    }
}