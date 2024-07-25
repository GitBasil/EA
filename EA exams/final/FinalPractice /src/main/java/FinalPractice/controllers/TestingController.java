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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import FinalPractice.domain.CustomerEvent;
import FinalPractice.domain.Item;
import FinalPractice.services.ApplicationProperties;
import FinalPractice.services.LogsService;

@RestController
@RequestMapping("/Testing")
public class TestingController {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    LogsService customerService;
    
    @RequestMapping(value = "/testJmsSender", method = RequestMethod.GET)
    public ResponseEntity<?> testJmsSender() {
        jmsTemplate.convertAndSend("testQueue","Message 1");
        
        return new  ResponseEntity<String>("Testing the post", HttpStatus.OK);
    }

    @GetMapping("/testKafkaSender")
    public ResponseEntity<?> testKafkaSender() {
        kafkaTemplate.send("topicA","Message 1");

        return new  ResponseEntity<String>("Testing the get", HttpStatus.OK);
    }

    @GetMapping("/testEventListener")
    public ResponseEntity<?> testEventListener() {

        CustomerEvent CustomerEvent = new CustomerEvent("Hello world");
        publisher.publishEvent(CustomerEvent);

        return new  ResponseEntity<String>("Testing the get", HttpStatus.OK);
    }

    @GetMapping("/testApplicationProperties")
    public ResponseEntity<?> testApplicationProperties() {

        System.out.println(applicationProperties);

        return new  ResponseEntity<String>("Testing the get", HttpStatus.OK);
    }

    @GetMapping("/testLogs")
    public ResponseEntity<?> testLogs() {

        customerService.fireLogs();

        return new  ResponseEntity<String>("Testing the get", HttpStatus.OK);
    }

    @GetMapping("/employees/{emp}")
    public ResponseEntity<?> employeeInfo(@PathVariable String emp) {
        return new  ResponseEntity<String>("Testing the get with " + emp, HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<?> employeeInfo2(@RequestParam String name) {
        return new  ResponseEntity<String>("Testing the get with " + name, HttpStatus.OK);
    }

    @GetMapping("/employee_class")
    public ResponseEntity<?> employeeInfo3(@RequestParam String name) {
        Item employee = new Item(name, name, name);
        return new  ResponseEntity<Item>(employee, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody Item employee){
        System.out.println(employee);
        return new ResponseEntity<Item>(employee,HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> delEmpl(@PathVariable String id){

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/contacts/{name}")
    public ResponseEntity<?> updateContact(@PathVariable String name, @RequestBody Item em) {

        return new ResponseEntity<Item>(em, HttpStatus.OK);
    }

}
