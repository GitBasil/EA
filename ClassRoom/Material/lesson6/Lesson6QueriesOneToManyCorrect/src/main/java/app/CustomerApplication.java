package app;

import domain.CreditCard;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.CustomerRepository;

import java.util.Date;
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
		CreditCard creditCard1 = new CreditCard("123", "Frank Brown", new Date());
		CreditCard creditCard2 = new CreditCard("345", "Frank Brown", new Date());
		Customer c1 = new Customer("Frank", "Brown");
		c1.getCreditcard().add(creditCard1);
		c1.getCreditcard().add(creditCard2);
		customerRepository.save(c1);

		CreditCard creditCard11 = new CreditCard("123", "Frank Johnson", new Date());
		CreditCard creditCard22 = new CreditCard("345", "Frank Johnson", new Date());
		Customer c2 = new Customer("Frank", "Johnson");
		c2.getCreditcard().add(creditCard11);
		c2.getCreditcard().add(creditCard22);
		customerRepository.save(c2);

//		List<Customer> customerList = customerRepository.findByFirstname("Frank");
//		customerList.stream().forEach(c -> System.out.println(c));

		List<Customer> customerList2 = customerRepository.findByFirstnameLazy("Frank");
		customerList2.stream().forEach(c -> System.out.println(c));

//		List<Customer> customerList3 = customerRepository.findByFirstnameEager("Frank");
//		customerList3.stream().forEach(c -> System.out.println(c));

	}
}
