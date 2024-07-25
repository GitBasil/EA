package bank.logging;

import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;

@Service
public class Logger implements ILogger{

	public void log(String logstring) {
		org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);
		logger.info(logstring);
	}

}
