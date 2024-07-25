
package jms;

import com.fasterxml.jackson.databind.ObjectMapper;

import jms.domain.Calculator;
import jms.domain.CalculatorTransaction;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CalculatorMessageListener {
    @JmsListener(destination = "calcQueue")
    public void receiveMessage(final String calcAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CalculatorTransaction calculatorTransaction = objectMapper.readValue(calcAsString, CalculatorTransaction.class);
            Calculator.performAction(calculatorTransaction);

            System.out.println("JMS receiver received message:" + calculatorTransaction);
            System.out.println("Result: " + Calculator.getResult());

        } catch (IOException e) {
            System.out.println("JMS receiver: Cannot convert : " + calcAsString+" to a CalculatorTransaction object");
        }
     }
}

