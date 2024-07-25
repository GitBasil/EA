package Lab6PartB.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String studentNumber;
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Department department;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stdId")
    private List<Grade> grades = new ArrayList<>();

    public Student() {
    }

    public Student(String studentNumber, String name, Department department) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.department = department;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Department getDepartment() {
        return department;
    }
    public List<Grade> getGrades() {
        return grades;
    }
    public void addGrade(Grade grade){
        grades.add(grade);
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", studentNumber='" + getStudentNumber() + "'" +
            ", department='" + getDepartment() + "'" +
            ", grades='" + getGrades() + "'" +
            "}";
    }
}
