package FinalPractice.Services.Receivers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerService {
    
    @KafkaListener(topics = {"TopicB"})
    public void Receiver(@Payload String message){
        System.out.println(message);
    }
    
}
