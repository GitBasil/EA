package FinalPractice.Services.Receivers;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import FinalPractice.classes.Item;

@Component
public class ApplicationEventListener {
    
    @EventListener
    public void Receiver(Item item){
        System.out.println(item);
    }
}
