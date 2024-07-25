package app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repositories.BookRepository;
import repositories.CustomerRepository;
import domain.Book;
import domain.Customer;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class CustomerApplication implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerrepository;
	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerrepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
		customerrepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
		customerrepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
		customerrepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
		customerrepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerrepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer by ID
		Optional<Customer> custOpt = customerrepository.findById(1L);
		Customer customer = custOpt.get();
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();




		bookRepository.save(new Book("Effective Java", "978-0134685991", "Joshua Bloch", 45.00));
		bookRepository.save(new Book("Clean Code", "978-0132350884", "Robert C. Martin", 42.35));
		bookRepository.save(new Book("Design Patterns", "978-0201633610", "Erich Gamma", 54.95));

		// fetch all books
		System.out.println("Books found with findAll():");
		System.out.println("-------------------------------");
		for (Book book : bookRepository.findAll()) {
			System.out.println(book);
		}
		System.out.println();

		Optional<Book> book = bookRepository.findById((long) 6);
		Book bk = book.get();
		bk.setPrice(150.0);
		bookRepository.save(bk);

		Optional<Book> book1 = bookRepository.findById((long) 7);
		Book bk1 = book.get();
		bookRepository.delete(bk1);

		// fetch all books
		System.out.println("Books found with findAll():");
		System.out.println("-------------------------------");
		for (Book bok : bookRepository.findAll()) {
			System.out.println(bok);
		}
		System.out.println();

	}
}
