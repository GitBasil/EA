package PersonPet.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PersonPet.domain.SQL.PersonSQL;
import PersonPet.domain.SQL.PetSQL;
import PersonPet.repositories.SQL.PersonRepositorySQL;

@Service
public class PersonServiceSQL {
    @Autowired
    PersonRepositorySQL personRepository;

    public void savePersons(List<PersonSQL> person)
    {
        personRepository.saveAll(person);
    }

    public List<PersonSQL> loadPersons(){
        return personRepository.findAll();
    }

    public List<PersonSQL> generatePersons(int numberOfPersons, int numberOfPetsPerPerson) {
        List<PersonSQL> persons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfPersons; i++) {
            List<PetSQL> pets = new ArrayList<>();
            for (int j = 0; j < numberOfPetsPerPerson; j++) {
                PetSQL pet = new PetSQL("PetName" + j, random.nextDouble() * 15); // Assign random age between 0 to 15
                pets.add(pet);
            }
            PersonSQL person = new PersonSQL("FirstName" + i, "LastName" + i, pets);
            persons.add(person);
        }

        return persons;
    }
}
