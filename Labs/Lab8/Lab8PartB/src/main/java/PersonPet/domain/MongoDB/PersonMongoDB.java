package PersonPet.domain.MongoDB;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class PersonMongoDB {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private List<PetMongoDB> pets = new ArrayList<>();

    public PersonMongoDB() {
    }

    public PersonMongoDB(long id, String firstName, String lastName, List<PetMongoDB> pets) {
        this.id = id;
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

    public List<PetMongoDB> getPets() {
        return this.pets;
    }

    public void setPets(List<PetMongoDB> pets) {
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
