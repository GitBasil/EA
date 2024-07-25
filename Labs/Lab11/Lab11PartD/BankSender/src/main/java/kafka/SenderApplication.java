package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.AccountParam;


@SpringBootApplication
@EnableKafka
public class SenderApplication implements CommandLineRunner {
    @Autowired
    Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createAccount();
        deposit();
        withdraw();
    }

    void createAccount(){
        AccountParam accountParam = new AccountParam();
		accountParam.setAccountNumber(1263831);
		accountParam.setCustomerName("Tom Frank");
		accountParam.setOperation("createAccount");

        ObjectMapper objectMapper = new ObjectMapper();
        String accountAsString="";
        try {
			accountAsString = objectMapper.writeValueAsString(accountParam);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

        System.out.println("Starting to send a message");
        sender.send("BankApplication", accountAsString);
        System.out.println("Message has been sent");
    }

    void deposit(){
        AccountParam accountParam = new AccountParam();
		accountParam.setAccountNumber(1263831);
		accountParam.setAmount(500);
		accountParam.setOperation("deposit");

        ObjectMapper objectMapper = new ObjectMapper();
        String accountAsString="";
        try {
			accountAsString = objectMapper.writeValueAsString(accountParam);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

        System.out.println("Starting to send a message");
        sender.send("BankApplication", accountAsString);
        System.out.println("Message has been sent");
    }

    void withdraw(){
        AccountParam accountParam = new AccountParam();
		accountParam.setAccountNumber(1263831);
		accountParam.setAmount(300);
		accountParam.setOperation("withdraw");

        ObjectMapper objectMapper = new ObjectMapper();
        String accountAsString="";
        try {
			accountAsString = objectMapper.writeValueAsString(accountParam);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

        System.out.println("Starting to send a message");
        sender.send("BankApplication", accountAsString);
        System.out.println("Message has been sent");
    }
}
