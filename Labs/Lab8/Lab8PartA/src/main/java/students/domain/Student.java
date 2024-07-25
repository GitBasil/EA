package students.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {
    @Id
    private long studentNumber;
    private String name;
    private String phone;
    private Address address;
    private List<Grade> grades = new ArrayList<>();

    public Student() {
    }

    public Student(long studentNumber, String name, String phone, Address address) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public long getStudentNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addGrade(Grade grade)
    {
        grades.add(grade);
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Grade> getGrades(){
        return grades;
    }

    @Override
    public String toString() {
        return "{" +
            " studentNumber='" + getStudentNumber() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", grades='" + getGrades() + "'" +
            "}";
    }
    

}
