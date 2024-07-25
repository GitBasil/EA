package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TaxMessageListener {
 
    @JmsListener(destination = "taxQueue")
    public void receiveMessage(final String JSMMessageString) {
        System.out.println("JMS receiver received message:" + JSMMessageString);
     }
}

