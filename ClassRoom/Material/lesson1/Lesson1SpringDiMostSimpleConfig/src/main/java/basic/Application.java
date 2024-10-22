package basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {
		ApplicationContext context = new
				AnnotationConfigApplicationContext(AppConfig.class);
		CustomerService customerService = context.getBean("customerService", CustomerService.class);
		customerService.addCustomer();
	}
}


