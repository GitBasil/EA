package FinalPractice.services;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver2 {

    @KafkaListener(topics = {"topicA"}, groupId = "gid2")
    public void receive(@Payload String message) {
        System.out.println("KafkaReceiver2: "+ message);
    }
    
}