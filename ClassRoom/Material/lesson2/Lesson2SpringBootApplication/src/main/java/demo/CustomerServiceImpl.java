package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private EmailService emailService;

    public void addCustomer() {
        emailService.sendEmail();
    }
}

