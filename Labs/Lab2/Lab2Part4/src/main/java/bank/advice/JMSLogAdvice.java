package bank.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import bank.logging.ILogger;

@Aspect
@Configuration
public class JMSLogAdvice {
    @Autowired
	private ILogger logger;
    @After("execution(* bank.jms.IJMSSender.*(..))")
    public void logJMSMessage(JoinPoint joinpoint) {
        logger.log("JMS method= "+joinpoint.getSignature().getName());
    }
}
