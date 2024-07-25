package jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;


@SpringBootApplication
@EnableJms
public class SpringJmsPersonSenderApplication implements CommandLineRunner {
	@Autowired
	JmsTemplate jmsTemplate;


	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJmsPersonSenderApplication.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		sendCalculator("+",24);
		sendCalculator("-",20);
		sendCalculator("*",2);
	}

	void sendCalculator(String operation, double num){
		CalculatorTransaction calculatorTransaction= new CalculatorTransaction(operation, num);
		//convert calc to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String calcAsString="";
		try {
			calcAsString = objectMapper.writeValueAsString(calculatorTransaction);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("Sending a JMS message:" + calcAsString);
		jmsTemplate.convertAndSend("calcQueue",calcAsString);
	}

	void sendPerson(){
		Person person = new Person("Frank", "Brown");
		//convert person to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String personAsString="";
		try {
			personAsString = objectMapper.writeValueAsString(person);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("Sending a JMS message:" + personAsString);
		jmsTemplate.convertAndSend("testQueue",personAsString);
	}

}
