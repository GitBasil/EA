package client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import generated.*;

public class CalculatorClient extends WebServiceGatewaySupport {

	public int addNumbers(int number1, int number2) {
		AddRequest request = new AddRequest();
		request.setNumber1(number1);
		request.setNumber2(number2);
		
		AddResponse response = (AddResponse) 
				getWebServiceTemplate().marshalSendAndReceive(request);
		return response.getResult();
	}
	public int subtractNumbers(int number1, int number2) {
		SubtractRequest request = new SubtractRequest();
		request.setNumber1(number1);
		request.setNumber2(number2);
		
		SubtractResponse response = (SubtractResponse) 
				getWebServiceTemplate().marshalSendAndReceive(request);
		return response.getResult();
	}
}


