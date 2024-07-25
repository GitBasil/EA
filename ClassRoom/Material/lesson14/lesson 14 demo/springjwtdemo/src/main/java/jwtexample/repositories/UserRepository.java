package jwtexample.repositories;

import java.util.Optional;
import jwtexample.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

  Optional<User> findByEmail(String email);
  
}
