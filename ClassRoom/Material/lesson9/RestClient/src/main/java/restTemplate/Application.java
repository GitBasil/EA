package restTemplate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	RestTemplate restTemplate = new RestTemplate();
	private String serverUrl = "http://localhost:8080/contacts";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// get frank
		Contact contact= restTemplate.getForObject(serverUrl+"/{firstName}", Contact.class, "Frank");
		System.out.println(contact);

		// add John
		restTemplate.postForLocation(serverUrl, new Contact("John","Doe", "jdoe@acme.com", "6739127563"));

		// get john
		contact= restTemplate.getForObject(serverUrl+"/{firstName}", Contact.class, "John");
		System.out.println(contact);

		// delete mary
		restTemplate.delete(serverUrl+"/{firstName}", "Mary");

		// update John
		contact.setEmail("johndoe@acme.com");
		restTemplate.put(serverUrl+"/{firstName}" , contact, "John");

		// get john
		contact= restTemplate.getForObject(serverUrl+"/{firstName}", Contact.class, "John");
		System.out.println(contact);
        // get all contacts
		Contacts contacts = restTemplate.getForObject(serverUrl, Contacts.class);
		System.out.println(contacts);
	}
}
