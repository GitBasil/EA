package FinalPractice.services;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {

    @KafkaListener(topics = {"topicA"})
    public void receive(@Payload String message) {
        System.out.println("KafkaReceiver: "+ message);
    }
    
}