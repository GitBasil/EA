package PersonPet.domain.SQL;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class PersonSQL {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PetSQL> pets = new ArrayList<>();

    public PersonSQL() {
    }

    public PersonSQL(String firstName, String lastName, List<PetSQL> pets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pets = pets;
    }

    public long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<PetSQL> getPets() {
        return this.pets;
    }

    public void setPets(List<PetSQL> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", pets='" + getPets() + "'" +
            "}";
    }
    
}
