package demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("One")
public class GreetingOne implements Greeting{
    public String getGreeting() {
        return "Hello World";
    }
}

