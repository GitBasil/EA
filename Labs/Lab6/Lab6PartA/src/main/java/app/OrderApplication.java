package app;

import domain.*;
import repositories.*;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Specifications.CDSpecifications;
import Specifications.CustomerSpecifications;
import Specifications.OrderSpecifications;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner{
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	OrderLineRepository orderLineRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CDRepository cdRepository;


	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Product product = new Product();
		product.setName("Hibernate 3");
		product.setDescription("Good book on Hibernate");
		product.setPrice(35.50);
		OrderLine ol1 = new OrderLine(2, product);

		Product product2 = new Product();
		product2.setName("The best of Queen");
		product2.setDescription("Album from 1995");
		product2.setPrice(12.98);
		OrderLine ol2 = new OrderLine(4, product2);

		Order o1 = new Order("234743", "12/10/06", "open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);

		Customer c1 = new Customer("Frank", "Brown", "USA", "Mainstreet 1",
				"New york", "43221");
		c1.addOrder(o1);
		o1.setCustomer(c1);

		customerRepository.save(c1);


		Product dvd = new DVD("Rocky3","DVD1 Description", 16, "DVD1");
		OrderLine ol3 = new OrderLine(2, dvd);


		Order o2 = new Order("123123", "12/10/06", "open");
		o2.addOrderLine(ol3);
		
		Customer c2 = new Customer("Brown", "Frank", "UK", "Mainstreet 1",
		"Amsterdam", "2389HJ");
		c2.addOrder(o2);
		o2.setCustomer(c2);
		customerRepository.save(c2);

		CD cd1 = new CD("CD1","CD1 Description", 16, "U2");
		CD cd2 = new CD("CD2","CD2 Description", 9, "U2");
		CD cd3 = new CD("CD3","CD3 Description", 25, "U3");
		cdRepository.saveAll(Arrays.asList(cd1, cd2, cd3));

//Give all customers. 
System.out.println(customerRepository.findBy());
System.out.println();

// // Give all CD’s from U2 with a price smaller than 10 euro
System.out.println(cdRepository.findByPriceLessThanAndArtist(10, "U2"));
System.out.println();

// // Give all customers with zip code 2389HJ 
System.out.println(customerRepository.findByAddressZip("2389HJ"));
System.out.println();

// // Give all customers who ordered a DVD with the name Rocky3 
System.out.println(customerRepository.findByTheOrdersOrderlinesProductName("Rocky3"));
System.out.println();

// Give all customers from the USA.
System.out.println(customerRepository.findCustomersFromCountry("USA"));
System.out.println();

// // Give all CD’s from U3
System.out.println(cdRepository.findByArtist("U3"));
System.out.println();
o2.setStatus("Closed");
orderRepository.save(o2);
System.out.println(orderRepository.allClosedOrders());
System.out.println();
//printOrder(o1);

// Give the first and lastnames of all customers who live in Amsterdam.
System.out.println(customerRepository.findCustomerInCity("Amsterdam"));
System.out.println();

// Give the ordernumbers of all orders from customers who live in a certain city (city is a parameter)
System.out.println(orderRepository.allOrdersFromCity("Amsterdam"));
System.out.println();
// Give all CD’s from a certain artist with a price bigger than a certain amount (artist and amount are parameter2)
System.out.println(cdRepository.findAllCDsWithArtistAndPriceBiggerThan("U2", 5));
System.out.println();

// // Give all addresses in Amsterdam. 
// System.out.println(addressRepository.findAllAddressInCity("Amsterdam"));
// System.out.println();

// // Give all CD’s from U2
// System.out.println(cdRepository.findAllCDsFromArtist("U2"));
// System.out.println();

// using specifications
// Give the ordernumbers of all orders with status ‘closed’
Specification<Order> closedOrds = OrderSpecifications.closedOrders();
List<Order> clsOrders = orderRepository.findAll(closedOrds);
System.out.println(clsOrders);
System.out.println();

// Give all customers who live in Amsterdam.
Specification<Customer> cc = CustomerSpecifications.CustomersLiveInCity("Amsterdam");
List<Customer> cinc = customerRepository.findAll(cc);
System.out.println(cinc);
System.out.println();

// Give all CD’s from a certain artist with a price bigger than a certain amount (artist and amount are parameter2).
Specification<CD> cdsp1 = CDSpecifications.getCDsWithArtist("U2");
Specification<CD> cdsp2 = CDSpecifications.getCDsWithPriceBiggerThan(5);
List<CD> cds = cdRepository.findAll(Specification.where(cdsp1).and(cdsp2));
System.out.println(cds);
	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrdernr());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstname() + " "
				+ cust.getLastname());
		for (OrderLine orderline : order.getOrderlines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
		}

	}
}
