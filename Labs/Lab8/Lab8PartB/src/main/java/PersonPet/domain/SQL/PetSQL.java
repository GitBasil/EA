package PersonPet.domain.SQL;

import jakarta.persistence.*;

@Entity
public class PetSQL {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double age;

    public PetSQL() {
    }

    public PetSQL(String name, double age) {
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return this.age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }
    
}
