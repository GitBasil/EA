package FinalPractice.services;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import FinalPractice.domain.*;

@Service
public class Listener {

    @Async
    @EventListener
    public void onEvent(CustomerEvent event) {
        System.out.println("received event :" + event.getMessage());;
    }
}
