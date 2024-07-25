package jms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
public class SpringJmsReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJmsReceiverApplication.class, args);
	}

}


