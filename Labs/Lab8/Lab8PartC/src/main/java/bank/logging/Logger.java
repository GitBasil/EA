package bank.logging;

import org.springframework.stereotype.Service;

@Service
public class Logger implements ILogger{

	public void log(String logstring) {
		System.out.println(logstring);
		java.util.logging.Logger.getLogger("BankLogger").info(logstring);		
	}

}
