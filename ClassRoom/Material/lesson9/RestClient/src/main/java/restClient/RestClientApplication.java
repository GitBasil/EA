package restClient;

import restTemplate.Contact;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();

        //get frank
        Contact frank = restClient.get()
                .uri("/contacts/{firstname}", "Frank")
                .retrieve()
                .body(Contact.class);
        System.out.println(frank);

        //add John
        Contact johnResponse = restClient.post()
                .uri("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Contact("John", "Doe", "jdoe@acme.com", "6739127563"))
                .retrieve()
                .body(Contact.class);

        // get john
        Contact john = restClient.get()
                .uri("/contacts/{firstname}", "John")
                .retrieve()
                .body(Contact.class);
        System.out.println(john);

        // delete mary
        restClient.delete()
                .uri("/contacts/{firstName}", "Mary")
                .retrieve()
                .toBodilessEntity();

        //update John
        john.setEmail("johndoe@acme.com");
        johnResponse = restClient.post()
                .uri("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(john)
                .retrieve()
                .body(Contact.class);

        //get john
        john = restClient.get()
                .uri("/contacts/{firstname}", "John")
                .retrieve()
                .body(Contact.class);
        System.out.println(john);

    }
}