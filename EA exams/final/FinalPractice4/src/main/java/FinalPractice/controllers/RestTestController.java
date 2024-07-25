package FinalPractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import FinalPractice.classes.*;

@RestController
@RequestMapping("/Testing")
public class RestTestController {

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
    public ResponseEntity<?> testSender(@PathVariable String sender){
        switch (sender) {
            case "JMS":
                jmsTemplate.convertAndSend("QueueA", "testSender JMS");
                break;
            case "KAFKA":
                kafkaTemplate.send("TopicA", "testSender KAFKA");
                break;
            case "EventPublisher":
                Item item = new Item("testSender EventPublish");
                applicationEventPublisher.publishEvent(item);
                break;
            default:
                break;
        }
        return new ResponseEntity<String>("testSender",HttpStatus.OK);
    }

    @GetMapping("/testConfig")
    public ResponseEntity<?> testConfig(@RequestParam String name){
        switch (name) {
            case "applicationProperties":
                System.out.println(applicationProperties);
                break;
            case "logs":
                fireLogs.doit();
                break;
            default:
                break;
        }
        return new ResponseEntity<String>("testConfig",HttpStatus.OK);
    }

    @GetMapping("getWithVar/{name}")
    public ResponseEntity<?> getWithVar(@PathVariable String name){
        return new ResponseEntity<>(name,HttpStatus.OK);
    }

    @GetMapping("/getWithParam")
    public ResponseEntity<?> getWithParam(@RequestParam String name){
        return new ResponseEntity<>(name,HttpStatus.OK);
    }

    @GetMapping("/getReturnClass")
    public ResponseEntity<?> getReturnClass(@RequestParam String name){
        Item item = new Item(name);

        return new ResponseEntity<Item>(item,HttpStatus.OK);
    }

    @RequestMapping(value = "/PostWithJson", method = RequestMethod.POST)
    public ResponseEntity<?> PostWithJson(@RequestBody Item item, @RequestParam String name){
        item.setMessage(item.getMessage() + " " + name);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @PutMapping("/PutWithJson")
    public ResponseEntity<?> PutWithJson(@RequestBody Item item){
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @DeleteMapping("/DeleteWithVar/{name}")
    public ResponseEntity<?> DeleteWithVar(@PathVariable String name){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
