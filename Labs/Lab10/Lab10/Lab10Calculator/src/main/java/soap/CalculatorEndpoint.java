package soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import generated.*;

@Endpoint 
public class CalculatorEndpoint { 
 
   @Autowired 
   Calculator calculator; 
 
   private static final String NAMESPACE_URI = "http://springtraining/calculator"; 
 
   @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddRequest") 
   @ResponsePayload 
   public AddResponse add(@RequestPayload AddRequest request) { 
      AddResponse response = new AddResponse(); 
      int calcresult= calculator.add(request.getNumber1(), request.getNumber2()); 
      response.setResult(calcresult); 
      return response; 
   } 
 
   @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SubtractRequest") 
   @ResponsePayload 
   public SubtractResponse add(@RequestPayload SubtractRequest request) { 
      SubtractResponse response = new SubtractResponse(); 
      int calcresult= calculator.subtract(request.getNumber1(), request.getNumber2()); 
      response.setResult(calcresult); 
      return response; 
   } 
} 