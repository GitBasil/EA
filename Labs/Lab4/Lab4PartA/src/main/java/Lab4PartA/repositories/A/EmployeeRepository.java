package Lab4PartA.repositories.A;

import org.springframework.data.jpa.repository.JpaRepository;

import Lab4PartA.domain.A.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findFirstByName(String name);
}
