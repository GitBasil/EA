package reference.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import reference.domain.CreditCard;


import java.util.List;

@Repository
public interface CreditCardRepository extends MongoRepository<CreditCard, Integer> {

}

