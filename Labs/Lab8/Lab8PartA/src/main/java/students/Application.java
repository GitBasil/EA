package students;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import students.domain.*;
import students.repositories.StudentRepositories;


@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    StudentRepositories studentRepositories;

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Address address1 = new Address("ST 1", "City 1", "Zip 1");
        Student st1 = new Student(1121, "Student 1", "123123", address1);
        studentRepositories.save(st1);

        Address address2 = new Address("ST 2", "City 2", "Zip 2");
        Student st2 = new Student(2222, "Student 2", "223223", address2);
        List<Grade> grades1 = new ArrayList<>(List.of(
            new Grade("Course 1","A"),
            new Grade("Course 2","B")
        ));
        st2.setGrades(grades1);
        studentRepositories.save(st2);

        Address address3 = new Address("ST 3", "City 3", "Zip 3");
        Student st3 = new Student(3323, "Student 3", "323323", address3);
        studentRepositories.save(st3);

        Address address4 = new Address("ST 4", "City 4", "Zip 4");
        Student st4 = new Student(4424, "Student 4", "423423", address4);
        List<Grade> grades4 = new ArrayList<>(List.of(
            new Grade("Course 1","A"),
            new Grade("Course 4","A+")
        ));
        st4.setGrades(grades4);
        studentRepositories.save(st4);

        List<Student> students = studentRepositories.findByName("Student 2");
        System.out.println(students);
        System.out.println();
        students = studentRepositories.findByPhone("323323");
        System.out.println(students);
        System.out.println();
        students = studentRepositories.findByAddressCity("City 4");
        System.out.println(students);
        System.out.println();
        students = studentRepositories.findByGradesCourseNameAndGradesGrade("Course 1","A");
        System.out.println(students);
        System.out.println();
        students = studentRepositories.findByGradesGrade("A+");
        System.out.println(students);
    }

}
