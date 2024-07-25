package Lab6PartB.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Lab6PartB.domain.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByDepartmentName(String name);
    @Query("SELECT s FROM Student s Join fetch s.department d WHERE d.name = :name")
    List<Student> findByDepartmentNameQuery(@Param("name") String name);

    List<Student> findByGradesCourseName(String name);
    @Query("SELECT distinct s FROM Student s Join fetch s.grades g Join fetch g.course c WHERE c.name = :name")
    List<Student> findByGradesCourseNameQuery(@Param("name") String name);
}