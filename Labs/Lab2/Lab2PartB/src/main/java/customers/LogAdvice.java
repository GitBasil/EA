package customers;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LogAdvice {
    @After("execution(* customers.EmailSender.sendEmail(..))")
    public void traceaftermethod(JoinPoint joinpoint) {
        String email = (String) joinpoint.getArgs()[0];
        String message= (String) joinpoint.getArgs()[1];

        IEmailSender emailSender = (EmailSender) joinpoint.getTarget();

        System.out.println(LocalDateTime.now() + ", method "+joinpoint.getSignature().getName() + ", address=" + email + 
                                ", message=" + message + ", outgoing mail server=" + emailSender.getOutgoingMailServer());
    }
}