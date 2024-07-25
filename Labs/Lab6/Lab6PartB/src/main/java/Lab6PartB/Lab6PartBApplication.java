package Lab6PartB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Lab6PartB.domain.Course;
import Lab6PartB.domain.Department;
import Lab6PartB.domain.Grade;
import Lab6PartB.domain.Student;
import Lab6PartB.repositories.StudentRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class Lab6PartBApplication implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Lab6PartBApplication.class, args);
	}
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		// Create some courses
        Course course1 = new Course("Introduction to Computer Science");
        Course course2 = new Course("Data Structures");
        Course course3 = new Course("Calculus I");
        Course course4 = new Course("Linear Algebra");

        // Create departments and assign courses to them
        Department csDepartment = new Department("Computer Science");
        Department mathDepartment = new Department("Mathematics");

        // Create students and assign departments
        Student student1 = new Student("S001", "Alice", csDepartment);
        Student student2 = new Student("S002", "Bob", csDepartment);
        Student student3 = new Student("S003", "Charlie", mathDepartment);
        Student student4 = new Student("S004", "Diana", mathDepartment);

        // Create grades for the students in courses
        Grade grade1 = new Grade(course1, 'A');
        Grade grade2 = new Grade(course2, 'B');
        Grade grade3 = new Grade(course1, 'B');
        Grade grade4 = new Grade(course2, 'C');
        Grade grade5 = new Grade(course3, 'A');
        Grade grade6 = new Grade(course4, 'B');
        Grade grade7 = new Grade(course3, 'C');
        Grade grade8 = new Grade(course4, 'A');

		student1.addGrade(grade1);
		student1.addGrade(grade2);
		student2.addGrade(grade3);
		student2.addGrade(grade4);
		student3.addGrade(grade5);
		student3.addGrade(grade6);
		student4.addGrade(grade7);
		student4.addGrade(grade8);

		studentRepository.saveAll(List.of(student1, student2, student3, student4));

		System.out.println(studentRepository.findByDepartmentName("Computer Science"));
		System.out.println();
		System.out.println(studentRepository.findByGradesCourseName("Data Structures"));

		System.out.println();
		System.out.println();

		System.out.println(studentRepository.findByDepartmentNameQuery("Mathematics"));
		System.out.println();
		System.out.println(studentRepository.findByGradesCourseNameQuery("Calculus I"));

	}


}
