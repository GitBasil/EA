package FinalPractice.controller;

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

import FinalPractice.Services.ApplicationProperties;
import FinalPractice.Services.LogService;
import FinalPractice.classes.CustomEvent;
import FinalPractice.classes.Item;

@RestController
@RequestMapping("/Testing")
public class TestingController {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    LogService logService;
    
    @GetMapping("/testJMSSender")
    public ResponseEntity<?> testJMSSender()
    {
        jmsTemplate.convertAndSend("queueA","Hello JMS");
        return new ResponseEntity<String>("testJMSSender", HttpStatus.OK);
    }

    @GetMapping("/testKafkaSender")
    public ResponseEntity<?> testKafkaSender()
    {
        kafkaTemplate.send("topicA", "Hello Kafka");
        return new ResponseEntity<>("testKafkaSender",HttpStatus.OK);
    }

    @GetMapping("/testEventListener")
    public ResponseEntity<?> testEventListener(){
        CustomEvent customEvent = new CustomEvent("applicationEventPublisher: CustomEvent");
        applicationEventPublisher.publishEvent(customEvent);
        return new ResponseEntity<String>("testEventListener",HttpStatus.OK);
    }

    @GetMapping("/testApplicationProperties")
    public ResponseEntity<?> testApplicationProperties(){
        System.out.println(applicationProperties);

        return new ResponseEntity<String>("applicationProperties",HttpStatus.OK);
    }

    @GetMapping("/testLogs")
    public ResponseEntity<?> testLogs(){
        logService.fireLogs();

        return new ResponseEntity<>("fireLogs", HttpStatus.OK);
    }

    @GetMapping("/getWithVar/{name}")
    public ResponseEntity<?> getWithVar(@PathVariable String name)
    {
        System.out.println("getWithVar: " + name);
        return new ResponseEntity<>("getWithVar", HttpStatus.OK);
    }

    @GetMapping("/getWithParam")
    public ResponseEntity<?> getWithParam(@RequestParam String name)
    {
        System.out.println("getWithParam: " + name);
        return new ResponseEntity<>("getWithParam",HttpStatus.OK);
    }

    @GetMapping("/getReturnClass")
    public ResponseEntity<?> getReturnClass(@RequestParam String name){
        Item item = new Item(name, name, name);
        return new ResponseEntity<Item>(item,HttpStatus.OK);
    }

    @PostMapping("/PostWithJson")
    public ResponseEntity<?> PostWithJson(@RequestBody Item item)
    {
        System.out.println(item);
        return new ResponseEntity<Item>(item,HttpStatus.OK);
    }

    @PutMapping("/PutWithJson")
    public ResponseEntity<?> PutWithJson(@RequestBody Item item){
        System.out.println(item);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    @DeleteMapping("DeleteWithVar/{name}")
    public ResponseEntity<?> DeleteWithVar(@PathVariable String name){
        System.out.println(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
