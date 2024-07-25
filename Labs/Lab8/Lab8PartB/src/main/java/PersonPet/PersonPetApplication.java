package PersonPet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import PersonPet.Services.PersonServiceMongoDB;
import PersonPet.Services.PersonServiceSQL;
import PersonPet.domain.MongoDB.PersonMongoDB;
import PersonPet.domain.SQL.PersonSQL;

@SpringBootApplication
public class PersonPetApplication implements CommandLineRunner {
	@Autowired
	PersonServiceSQL personServiceSQL;
	@Autowired
	PersonServiceMongoDB personServiceMongoDB;

	public static void main(String[] args) {
		SpringApplication.run(PersonPetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<PersonSQL> personsSQLs = personServiceSQL.generatePersons(10000, 10);
		personServiceSQL.savePersons(personsSQLs);
		personServiceSQL.loadPersons();

		List<PersonMongoDB> personMongoDBs = personServiceMongoDB.generatePersons(10000, 10);
		personServiceMongoDB.savePersons(personMongoDBs);
		personServiceMongoDB.loadPersons();
	}

}
