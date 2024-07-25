package app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		insertCustomers();
		retrieveCustomers();
		updateCustomers();

		insertSchools();
		retrieveSchools();
		retrieveSchoolsWithStudents();
	}

	private void insertCustomers() {
		for (int x=0; x<5000; x++) {
			Customer customer = new Customer("John Doe "+x);
			Account account = new Account("123"+x);
			customer.setAccount(account);
			customerRepository.save(customer);
			System.out.println("Inserting customer  "+x);
		}
	}

	private void retrieveCustomers() {
		System.out.println("Retrieving all customers ...");
		long start = System.currentTimeMillis();

		@SuppressWarnings("unused")
		List<Customer> customers = customerRepository.findAll();
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To retrieve all Customers took "+timeElapsed+" ms");
	}

	private void updateCustomers() {
		System.out.println("Change the name of all customers ...");
		long start = System.currentTimeMillis();

		// List<Customer> customers = customerRepository.findAll();
		// for(Customer c: customers){
		// 	c.setName("James Bond");
		// 	customerRepository.save(c);
		// }

		customerRepository.changeCustomerName("James Bond");

		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To change the name of all customers took "+timeElapsed+" ms");
	}

	private void insertSchools() {
		for (int i = 1; i <= 100; i++) {
            String schoolName = "School " + i;
            List<Student> students = new ArrayList<>();

            for (int j = 1; j <= 10; j++) {
				String studentFirstName = "StudentFirstName " + j;
                String studentLastName = "StudentLastName " + j;
                String studentEmail = "student" + j + "@school" + i + ".com";
                Student student = new Student(studentFirstName, studentLastName, studentEmail, null);
                students.add(student);
            }
			School school = new School(schoolName, students);


            for (Student student : students) {
                student.setSchool(school);
            }
			
			schoolRepository.save(school);
			System.out.println("Inserting school  "+i);
		}
	}
	
	private void retrieveSchools() {
		System.out.println("Retrieving all Schools ...");
		long start = System.currentTimeMillis();

		List<School> schools = schoolRepository.findAll();
		schools.forEach(f -> System.out.println(f.getName()));
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To retrieve all Schools took "+timeElapsed+" ms");
	}

	@Transactional
	private void retrieveSchoolsWithStudents() {
		System.out.println("Retrieving all Schools ...");
		long start = System.currentTimeMillis();

		List<School> schools = schoolRepository.findAllWithStudents();
		schools.forEach(f -> f.getStudents().forEach(s -> System.out.println(s.getFirstname())));
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To retrieve all Schools took "+timeElapsed+" ms");
	}
}
