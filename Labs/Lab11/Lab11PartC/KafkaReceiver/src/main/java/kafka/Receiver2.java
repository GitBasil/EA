package kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver2 {

    @KafkaListener(topics = {"topicA2"}, groupId = "gid2")
    public void receive2(@Payload String message) {
        System.out.println("Receiver2 received message= "+ message);
    }

}