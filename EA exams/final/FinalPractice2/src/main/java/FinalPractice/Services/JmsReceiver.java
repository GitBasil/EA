package FinalPractice.Services;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {
    
    @JmsListener(destination = "queueA")
    public void JmsListenerA(String message){
        System.out.println("JmsListenerA: " + message);
    }
}
