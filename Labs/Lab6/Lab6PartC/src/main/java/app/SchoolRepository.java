package app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchoolRepository extends JpaRepository<School, Long> {
    @Query("SELECT s FROM School s JOIN FETCH s.students")
    List<School> findAllWithStudents();
}




