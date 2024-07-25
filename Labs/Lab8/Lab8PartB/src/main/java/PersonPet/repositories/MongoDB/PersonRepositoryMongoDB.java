package PersonPet.repositories.MongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

import PersonPet.domain.MongoDB.PersonMongoDB;

public interface PersonRepositoryMongoDB extends MongoRepository<PersonMongoDB, Integer> {
    
}
