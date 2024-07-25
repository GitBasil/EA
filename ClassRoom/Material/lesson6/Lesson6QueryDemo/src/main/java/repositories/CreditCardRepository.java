package repositories;

import domain.CreditCard;
import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
   CreditCard findByNumber(String number);
   CreditCard findByNumberAndName(String number, String name);
   List<CreditCard> findByName(String name);

}
