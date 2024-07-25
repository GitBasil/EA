package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import domain.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);
    Optional<Customer> findByEmail(String email);
    Customer findByFirstNameAndLastName(String fistName, String lastName);
    List<Customer> findFirst2By(); 
    
    @Query("select c.lastName from Customer c where c.firstName= :firstName")
    String findLastNameByFirstName(@Param("firstName") String firstName);

}




