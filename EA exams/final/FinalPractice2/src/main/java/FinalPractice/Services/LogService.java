package FinalPractice.Services;

import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class LogService {
    Logger logger = LoggerFactory.getLogger(LogService.class);

    public void fireLogs(){
        addTrace();
        addDebug();
        addInfo();
        addWarn();
        addError();
    }

    public void addTrace(){
        logger.trace("addTrace");
    }
    public void addDebug(){
        logger.debug("addDebug");
    }
    public void addInfo(){
        logger.info("addInfo");
    }
    public void addWarn(){
        logger.warn("addWarn");
    }
    public void addError(){
        logger.error("addError");
    }

}
