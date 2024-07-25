package reference;

import reference.domain.Address;
import reference.domain.CreditCard;
import reference.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reference.repository.CreditCardRepository;
import reference.repository.CustomerRepository;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CreditCardRepository creditCardRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/26");
		customer.addCreditCard(creditCard);
		CreditCard creditCard2 = new CreditCard("4532145657", "MasterCard", "11/27");
		customer.addCreditCard(creditCard2);
		creditCardRepository.save(creditCard);
		creditCardRepository.save(creditCard2);
		Address address = new Address("Mainstreet 23", "Chicago", "65231");
		customer.setAddress(address);
		customerRepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("12432511177", "Visa", "11/26");
		customer.addCreditCard(creditCard);
		creditCard2 = new CreditCard("4367894532", "MasterCard", "11/27");
		customer.addCreditCard(creditCard2);
		creditCardRepository.save(creditCard);
		creditCardRepository.save(creditCard2);
		address = new Address("Mainstreet 44", "New York", "21231");
		customer.setAddress(address);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("77788999044", "Visa", "11/26");
		customer.addCreditCard(creditCard);
		creditCard2 = new CreditCard("4444563332", "MasterCard", "11/27");
		customer.addCreditCard(creditCard2);
		creditCardRepository.save(creditCard);
		creditCardRepository.save(creditCard2);
		address = new Address("1000 n 4th street", "Fairfield", "52556");
		customer.setAddress(address);
		customerRepository.save(customer);

		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.findAll());

		System.out.println("-----------find by phone ----------------");
		System.out.println(customerRepository.findByPhone("0622341678"));


		System.out.println("-----------find by name ----------------");
		System.out.println(customerRepository.findByName("John doe"));

	}

}
