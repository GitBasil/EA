package app;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class School {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Student> students = new ArrayList<>();

    public School() {
    }

    public School(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", students='" + getStudents() + "'" +
            "}";
    }
    
}
