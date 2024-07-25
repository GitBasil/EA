package Lab4PartA;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Lab4PartA.domain.A.Department;
import Lab4PartA.domain.A.Employee;
import Lab4PartA.domain.B.Book;
import Lab4PartA.domain.B.Publisher;
import Lab4PartA.domain.C.Flight;
import Lab4PartA.domain.C.Passenger;
import Lab4PartA.domain.D.School;
import Lab4PartA.domain.D.Student;
import Lab4PartA.repositories.A.DepartmentRepository;
import Lab4PartA.repositories.A.EmployeeRepository;
import Lab4PartA.repositories.B.BookRepository;
import Lab4PartA.repositories.B.PublisherRepository;
import Lab4PartA.repositories.C.FlightRepository;
import Lab4PartA.repositories.C.PassengerRepository;
import Lab4PartA.repositories.D.SchoolRepository;
import Lab4PartA.repositories.D.StudentRepository;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class Lab4PartAApplication implements CommandLineRunner {
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	PublisherRepository publisherRepository;
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	PassengerRepository passengerRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	SchoolRepository schoolRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Lab4PartAApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Part A
		Department dep = new Department("Department A");
		
		Employee emp = new Employee("Employee 1", dep);
		dep.addEmployee(emp);
		Employee emp1 = new Employee("Employee 2", dep);
		dep.addEmployee(emp1);
		
		departmentRepository.save(dep);

		dep = departmentRepository.findFirstByName("Department A");
		System.out.println(dep);
		//----------------//
		System.out.println();
		//Part B
		Publisher pb = new Publisher("Publisher A");
		
		Book bk = new Book("ISBN 1","Title 1","Author 1",pb);
		pb.addBook(bk);
		Book bk1 = new Book("ISBN 2","Title 2","Author 2",pb);
		pb.addBook(bk1);
		
		publisherRepository.save(pb);

		pb = publisherRepository.findFirstByName("Publisher A");
		System.out.println(pb);
		//----------------//
		System.out.println();
		//Part C
		Passenger ps = new Passenger("Passenger A");
		
		Flight fl = new Flight("#1","F1", "T1", LocalDate.now());
		ps.addFlight(fl);
		Flight fl1 = new Flight("#2","F2", "T2", LocalDate.now());
		ps.addFlight(fl1);
		
		passengerRepository.save(ps);

		ps = passengerRepository.findFirstByName("Passenger A");
		System.out.println(ps);
		//----------------//
		System.out.println();
		//Part D
		School sc = new School("School A");
		
		Student st = new Student("FirstName 1", "LastName 1");
		sc.addStudent(st);
		Student st1 = new Student("FirstName 2", "LastName 2");
		sc.addStudent(st1);
		
		schoolRepository.save(sc);

		sc = schoolRepository.findFirstByName("School A");
		System.out.println(sc);
		//----------------//
	}
	

}
