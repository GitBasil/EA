package Lab6PartB.domain;

import jakarta.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Department() {
    }

    public Department(String name) {
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
