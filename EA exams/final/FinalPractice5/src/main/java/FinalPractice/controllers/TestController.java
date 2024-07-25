package FinalPractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import FinalPractice.classes.Item;
import FinalPractice.services.ApplicationProperties;
import FinalPractice.services.FireLogs;

@RestController
@RequestMapping("/Testing")
public class TestController {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    FireLogs fireLogs;
    
    @GetMapping("/testSender/{sender}")
    public ResponseEntity<?> testSender(@PathVariable String sender)
    {
        switch(sender){
            case "JMS":
                jmsTemplate.convertAndSend("QueueA","Testing Senders: " + sender);
                break;
            case "KAFKA":
                kafkaTemplate.send("TopicA","Testing Senders: " + sender);
                break;
            case "EventPublisher":
                Item item = new Item("Testing Senders: " + sender);
                applicationEventPublisher.publishEvent(item);
                break;
            default:
            break;
        }
        return new ResponseEntity<>("Testing Senders: " + sender, HttpStatus.OK);
    }

    @GetMapping("/testConfig")
    public ResponseEntity<?> testConfig(@RequestParam String name){
        switch (name) {
            case "applicationProperties":
                System.out.println(applicationProperties);
                break;
            case "logs":
                fireLogs.fireit();
                break;
            default:
                break;
        }
        return new ResponseEntity<>("Testing Config: " + name,HttpStatus.OK);
    }

    @GetMapping("/getWithVar/{name}")
    public ResponseEntity<?> getWithVar(@PathVariable String name)
    {
        return new ResponseEntity<>(name,HttpStatus.OK);
    }
    @GetMapping("/getWithParam")
    public ResponseEntity<?> getWithParam(@RequestParam String name)
    {
        return new ResponseEntity<>(name,HttpStatus.OK);
    }
    @GetMapping("/getReturnClass")
    public ResponseEntity<?> getReturnClass(@RequestParam String name)
    {
        return new ResponseEntity<>(new Item(name),HttpStatus.OK);
    }
    @PostMapping("/PostWithJson")
    public ResponseEntity<?> getWithVar(@RequestBody Item item, @RequestParam String name)
    {
        item.setMessage(name);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }
    @PutMapping("/PutWithJson")
    public ResponseEntity<?> PutWithJson(@RequestBody Item item)
    {
        return new ResponseEntity<>(item,HttpStatus.OK);
    }
    @DeleteMapping("/DeleteWithVar/{name}")
    public ResponseEntity<?> DeleteWithVar(@PathVariable String name)
    {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
