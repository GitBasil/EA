package FinalPractice.Services;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import FinalPractice.classes.CustomEvent;

@Component
public class CustomEventListener {
    
    @Async
    @EventListener
    public void onEvent1(CustomEvent customEvent)
    {
        System.out.println("onEvent1: " + customEvent);
    }

    @Async
    @EventListener
    public void onEvent2(CustomEvent customEvent)
    {
        System.out.println("onEvent2: " + customEvent);
    }
}
