package FinalPractice.services;

import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class FireLogs {
    Logger logger = LoggerFactory.getLogger(FireLogs.class);
    
    public void fireit(){
        addTRACE();
        addDEBUG();
        addINFO();
        addWARN();
        addERROR();
    }

    public void addTRACE(){logger.trace("addTRACE");}
    public void addDEBUG(){logger.debug("addDEBUG");}
    public void addINFO(){logger.info("addINFO");}
    public void addWARN(){logger.warn("addWARN");}
    public void addERROR(){logger.error("addERROR");}
}
