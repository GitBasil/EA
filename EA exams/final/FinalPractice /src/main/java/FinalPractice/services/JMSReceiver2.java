package FinalPractice.services;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSReceiver2 {
    
    @JmsListener(destination =  "testQueue")
    public void getMessage(String message)
    {
        System.out.println("JMSReceiver2: " + message);
    }
}
