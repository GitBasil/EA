package app;

import domain.Address;
import domain.CreditCard;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.AddressRepository;
import repositories.CreditCardRepository;
import repositories.CustomerRepository;

import java.util.Date;
import java.util.List;


@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CreditCardRepository creditcardRepository;
	@Autowired
	AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		CreditCard creditCard1 = new CreditCard("123", "Frank Brown", new Date());
		CreditCard creditCard2 = new CreditCard("345", "Frank Brown", new Date());
		Customer c1 = new Customer("Frank", "Brown");
		c1.getCreditcard().add(creditCard1);
		c1.getCreditcard().add(creditCard2);
		Address a1 = new Address("mainstreet 1", "Chicago", "58902");
		c1.setAddress(a1);

		customerRepository.save(c1);

		CreditCard creditCard11 = new CreditCard("356", "Frank Johnson", new Date());
		CreditCard creditCard22 = new CreditCard("666", "Frank Johnson", new Date());
		Customer c2 = new Customer("Frank", "Johnson");
		c2.getCreditcard().add(creditCard11);
		c2.getCreditcard().add(creditCard22);
		Address a2 = new Address("mainstreet 4", "New York", "21345");
		c2.setAddress(a2);
		customerRepository.save(c2);

		System.out.println("Get creditcard by number 356");
		System.out.println(creditcardRepository.findByNumber("356"));
		System.out.println("------------------------");

		System.out.println("Get creditcard by number 356 and name Frank Johnson");
		System.out.println(creditcardRepository.findByNumberAndName("356", "Frank Johnson"));
		System.out.println("------------------------");


		System.out.println("Get creditcards from Frank Johnson");
		creditcardRepository.findByName("Frank Johnson").stream().forEach(n -> System.out.println(n));
		System.out.println("------------------------");

		System.out.println("Get customers from Chicago");
		customerRepository.findCustomerByCity("Chicago").stream().forEach(n -> System.out.println(n));
		System.out.println("------------------------");

		System.out.println("Get customers with zip 21345");
		customerRepository.findByAddressZip("21345").stream().forEach(n -> System.out.println(n));
		System.out.println("------------------------");

		System.out.println("Get customers with creditcard number 356");
		customerRepository.findCustomerByCreditcard("356").stream().forEach(n -> System.out.println(n));
		System.out.println("------------------------");

		System.out.println("Get addresses from New York");
		addressRepository.findByCity("New York").stream().forEach(n -> System.out.println(n));
		System.out.println("------------------------");

		System.out.println("Get all zip codes from New York");
		addressRepository.findZipcodesByCity("New York").stream().forEach(n -> System.out.println(n));
		System.out.println("------------------------");

		System.out.println("Get customers with creditcard number 123");
		customerRepository.findByCreditcardsNumber("123").stream().forEach(n -> System.out.println(n));
		System.out.println("------------------------");
	}
}
