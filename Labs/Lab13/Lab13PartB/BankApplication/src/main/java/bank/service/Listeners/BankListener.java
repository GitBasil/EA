package bank.service.Listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import bank.domain.TraceLog;
import bank.repositories.TraceLogRepository;

@Service
public class BankListener {

    @Autowired
    TraceLogRepository traceLogRepository;
    
    @EventListener
    public void onEvent(TraceLog event) {
        System.out.println("Received event :" + event);
    }
    
    @EventListener
    public void onEventDB(TraceLog event) {
        traceLogRepository.save(event);
    }
}
