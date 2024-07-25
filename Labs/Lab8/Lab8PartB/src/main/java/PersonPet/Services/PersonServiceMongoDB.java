package PersonPet.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PersonPet.domain.MongoDB.*;
import PersonPet.repositories.MongoDB.PersonRepositoryMongoDB;

@Service
public class PersonServiceMongoDB {
    @Autowired
    PersonRepositoryMongoDB personRepository;

    public void savePersons(List<PersonMongoDB> person)
    {
        personRepository.saveAll(person);
    }

    public List<PersonMongoDB> loadPersons(){
        return personRepository.findAll();
    }

    public List<PersonMongoDB> generatePersons(int numberOfPersons, int numberOfPetsPerPerson) {
        List<PersonMongoDB> persons = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= numberOfPersons; i++) {
            List<PetMongoDB> pets = new ArrayList<>();
            for (int j = 0; j < numberOfPetsPerPerson; j++) {
                PetMongoDB pet = new PetMongoDB("PetName" + j, random.nextDouble() * 15); // Assign random age between 0 to 15
                pets.add(pet);
            }
            PersonMongoDB person = new PersonMongoDB(i, "FirstName" + i, "LastName" + i, pets);
            persons.add(person);
        }

        return persons;
    }
}
