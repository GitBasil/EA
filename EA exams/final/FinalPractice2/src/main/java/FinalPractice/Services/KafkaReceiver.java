package FinalPractice.Services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {
    
    @KafkaListener(topics = {"topicA"})
    public void KafkaListenerA(@Payload String message){
        System.out.println("KafkaListenerA: " + message);
    }
}
