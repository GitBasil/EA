package Lab4PartA.domain.D;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class School {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @MapKey(name="studentId")
    private Map<Integer, Student> students = new HashMap<Integer, Student>();

    public School() {
    }

    public School( String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Student>  getStudents() {
        return students;
    }
    public void addStudent(Student student){
        students.put(student.getStudentId(), student);
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students='" + students + '\'' +
                '}';
    }
}