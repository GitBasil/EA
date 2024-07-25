package FinalPractice.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledCheck {
    
    @Scheduled(initialDelay = 1000, fixedRate = 5000)
    public void welcome() {
        System.out.println("Welcome !!");
    }
}
