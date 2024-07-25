package FinalPractice.Services.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FinalPractice.classes.Item;

@RestController
@RequestMapping("/Testing")
public class TestingController {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    
    @GetMapping("/PubSub/{senderType}")
    public ResponseEntity<?> testJmsSender(@PathVariable String senderType){

        switch (senderType) {
            case "JMS":
                jmsTemplate.convertAndSend("QueueA","Testing Jms Sender");
                break;
            case "KAFKA":
                kafkaTemplate.send("TopicB", "Testing Kafka Sender");
                break;
            case "EventListener":
                Item item = new Item("Testing Application Event Publisher");
                applicationEventPublisher.publishEvent(item);
                break;
            default:
                break;
        }
        
        return new ResponseEntity<>("testSender",HttpStatus.OK);
    }
}
