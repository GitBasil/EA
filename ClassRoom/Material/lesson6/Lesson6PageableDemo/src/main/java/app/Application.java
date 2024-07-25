package app;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.ProductRepository;
import java.util.List;


@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        for (int x=1; x<50; x++){
			productRepository.save(new Product("IPhone"+x, 980.00+x, "phones", "Apple"));
			productRepository.save(new Product("Samsung"+x, 875.00+x, "phones", "Samsung"));
		}


		System.out.println("Get the first 10 products");
		Page<Product> productPage=  productRepository.findAll(PageRequest.of(0, 9));
		productPage.forEach(p -> System.out.println(p));
		System.out.println("Total number of elements = "+productPage.getTotalElements());
		System.out.println("Total number of pages = "+productPage.getTotalPages());
		System.out.println("------------------------");

		System.out.println("Get the first 5 products from category phones");
		List<Product> products= (List<Product>) productRepository.findAllByCategory("phones", PageRequest.of(0, 5));
		products.stream().forEach(p -> System.out.println(p));
		System.out.println("------------------------");

		System.out.println("Get the first 10 products but sort by name");
		productPage=  productRepository.findAll(PageRequest.of(0, 9, Sort.by("name")));
		productPage.forEach(p -> System.out.println(p));
		System.out.println("------------------------");
	}
}
