package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CustomerService customerService() {
        CustomerService customerService = new CustomerService();
        customerService.setCustomerDAO(customerDAO());
        customerService.setEmailSender(emailSender());

        return customerService;
    }

    @Bean
    public CustomerDAO customerDAO(){
        ILogger logger = new Logger();
        CustomerDAO customerDAO = new CustomerDAO(logger);

        return customerDAO;
    }

    @Bean
    public EmailSender emailSender(){
        ILogger logger = new Logger();
        EmailSender emailSender = new EmailSender(logger);

        return emailSender;
    }
}
