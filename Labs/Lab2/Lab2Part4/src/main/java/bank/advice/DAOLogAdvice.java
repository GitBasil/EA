package bank.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import bank.logging.ILogger;

@Aspect
@Configuration
public class DAOLogAdvice {
    @Autowired
	private ILogger logger;

    @After("execution(* bank.dao.IAccountDAO.*(..))")
    public void logDAOCall(JoinPoint joinpoint) {
        logger.log("DAO method= "+joinpoint.getSignature().getName());
    }
}
