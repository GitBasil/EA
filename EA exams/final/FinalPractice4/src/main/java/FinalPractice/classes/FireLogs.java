package FinalPractice.classes;

import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class FireLogs {
    Logger logger = LoggerFactory.getLogger(FireLogs.class);

    public void doit(){
       addTRACE();
       addDEBUG();
       addINFO();
       addWARN();
       addERROR();
    }

    public void addTRACE(){logger.trace("addTrace");}
    public void addDEBUG(){logger.debug("addDEBUG");}
    public void addINFO(){logger.info("addINFO");}
    public void addWARN(){logger.warn("addWARN");}
    public void addERROR(){logger.error("addERROR");}
}
