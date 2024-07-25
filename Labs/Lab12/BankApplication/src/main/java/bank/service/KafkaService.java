package bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.DTO.AccountParam;

@Service
@EnableKafka
public class KafkaService {

    @Autowired
    IAccountService accountService;

    @KafkaListener(topics = {"BankApplication"})
    public void createAccount(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AccountParam accountParam = objectMapper.readValue(message, AccountParam.class);
            
            switch (accountParam.getOperation()) {
                case "createAccount":
                    System.out.println("Receiver received <createAccount> message= "+ accountParam);
                    accountService.createAccount(accountParam.getAccountNumber(), accountParam.getCustomerName());
                    break;
                case "deposit":
                    System.out.println("Receiver received <deposit> message= "+ accountParam);
                    accountService.deposit(accountParam.getAccountNumber(), accountParam.getAmount());
                    break;
                case "withdraw":
                    System.out.println("Receiver received <withdraw> message= "+ accountParam);
                    accountService.withdraw(accountParam.getAccountNumber(), accountParam.getAmount());
                    break;
            
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
