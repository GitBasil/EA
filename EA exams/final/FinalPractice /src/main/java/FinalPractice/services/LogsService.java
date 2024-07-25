package FinalPractice.services;

import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class LogsService {
    Logger logger = LoggerFactory.getLogger(LogsService.class);


    public void fireLogs() {
        addTrace();
        addDebug();
        addInfo();
        addWarn();
        addError();
    }

    public void addTrace(){
        logger.trace("A TRACE Message");
    }
    public void addDebug(){
        logger.debug("A DEBUG Message");
    }
    public void addInfo(){
        logger.info("An INFO Message");
    }
    public void addWarn(){
        logger.warn("A WARN Message");
    }
    public void addError(){
        logger.error("An ERROR Message");
    }
}
