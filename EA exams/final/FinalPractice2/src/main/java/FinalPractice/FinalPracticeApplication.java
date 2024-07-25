package FinalPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import FinalPractice.Services.ApplicationProperties;

@SpringBootApplication
@EnableJms
@EnableKafka
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties(ApplicationProperties.class)
public class FinalPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalPracticeApplication.class, args);
	}

}
