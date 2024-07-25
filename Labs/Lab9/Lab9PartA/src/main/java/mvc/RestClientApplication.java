package mvc;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import mvc.domain.Book;

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
        //add John
        Book book = restClient.post()
            .uri("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .body(new Book("isnb", "author", "title", 10))
            .retrieve()
            .body(Book.class);

        System.out.println(book);


    }
}
