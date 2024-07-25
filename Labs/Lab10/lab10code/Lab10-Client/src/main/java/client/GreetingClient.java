package client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import generated.*;

public class GreetingClient extends WebServiceGatewaySupport {

	public String getMessage(Person person) {
		GreetingRequest request = new GreetingRequest();
		request.setPerson(person);
		
		GreetingResponse response = (GreetingResponse) 
				getWebServiceTemplate().marshalSendAndReceive(request);
		return response.getGreeting();
	}
}


