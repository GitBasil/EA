package students.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import students.domain.Student;

public interface StudentRepositories extends MongoRepository<Student, Integer> {
    
    public List<Student> findByName(String name);
    public List<Student> findByPhone(String phone);
    public List<Student> findByAddressCity(String city);
    public List<Student> findByGradesCourseNameAndGradesGrade(String courseName, String grade);
    public List<Student> findByGradesGrade(String grade);

}
