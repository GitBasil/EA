package FinalPractice.Services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingService {

    private int i=0;
    @Scheduled(fixedRate = 5000, initialDelay = 1000)
    public void loopPrint(){
        System.out.println("Welcome !!" + i);
        i++;
    }
}
