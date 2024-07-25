package Lab6PartB.domain;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
    
}
