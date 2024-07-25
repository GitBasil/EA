package FinalPractice.services;

import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import FinalPractice.classes.Item;

@Component
public class TestServices {
    
    @JmsListener(destination = "QueueA")
    public void JmsReceiver(String message){
        System.out.println(message);
    }

    @KafkaListener(topics = {"TopicA"})
    public void KafkaReceiver(@Payload String message){
        System.out.println(message);
    }

    @EventListener
    public void EventReceiver(Item item){
        System.out.println(item);
    }

    @Scheduled(fixedRate=5000)
    public void printScheduled(){
        System.out.println("Welcome !!");
    }
    
}
