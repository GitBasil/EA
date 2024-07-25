package soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import generated.GreetingRequest;
import generated.GreetingResponse;



@Endpoint
public class GreetingEndpoint {
	private static final String NAMESPACE_URI = "http://springtraining/greeting";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GreetingRequest")
	@ResponsePayload
	public GreetingResponse getGreeting(@RequestPayload GreetingRequest request) {
		GreetingResponse response = new GreetingResponse();
		response.setGreeting(request.getPerson().getFirstName()+" "+request.getPerson().getLastName());
		return response;
	}
}



