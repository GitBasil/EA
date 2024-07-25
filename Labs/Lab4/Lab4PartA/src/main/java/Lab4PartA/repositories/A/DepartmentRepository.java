package Lab4PartA.repositories.A;

import org.springframework.data.jpa.repository.JpaRepository;

import Lab4PartA.domain.A.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findFirstByName(String name);
}
