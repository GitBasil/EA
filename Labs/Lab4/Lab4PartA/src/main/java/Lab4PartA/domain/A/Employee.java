package Lab4PartA.domain.A;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long employeeNumber;
    private String name;
    @ManyToOne
    private Department department;

    public Employee() {}

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", name='" + name + '\'' +
                ", department=" + department.getName() +
                '}';
    }
}
