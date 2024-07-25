package FinalPractice.Services.Receivers;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsListenerService {
    
    @JmsListener(destination = "QueueA")
    public void jmsReceiver(String message)
    {
        System.out.println(message);
    }
}
