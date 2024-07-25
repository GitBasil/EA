package app;

import domain.Address;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.CustomerRepository;

import java.util.List;


@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class CustomerApplication implements CommandLineRunner{
	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Address a1 = new Address("mainstreet 1", "Chicago", "58902");
		Customer c1 = new Customer("Frank", "Brown");
		c1.setAddress(a1);
		customerRepository.save(c1);

		Address a2 = new Address("mainstreet 4", "New York", "21345");
		Customer c2 = new Customer("Frank", "Johnson");
		c2.setAddress(a2);
		customerRepository.save(c2);

//		List<Customer> customerList = customerRepository.findByFirstname("Frank");
//		customerList.stream().forEach(c -> System.out.println(c));

		List<Customer> customerList2 = customerRepository.findByFirstnameLazy("Frank");
		customerList2.stream().forEach(c -> System.out.println(c));

//		List<Customer> customerList3 = customerRepository.findByFirstnameEager("Frank");
//		customerList3.stream().forEach(c -> System.out.println(c));

	}
}
