package contacts;

import contacts.service.ContactDTO;
import contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;


@SpringBootApplication
public class SpringBootDemoApplication implements CommandLineRunner {
	@Autowired
	private ContactService contactService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		contactService.add(new ContactDTO("Frank","Brown","fbrown@gmail.com","4723459800"));
		System.out.println(contactService.findByFirstName("Frank"));
	}
}
