package Lab4PartA.repositories.D;

import org.springframework.data.jpa.repository.JpaRepository;

import Lab4PartA.domain.D.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findFirstByfirstName(String firstName);
}
