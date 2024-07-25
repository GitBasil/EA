package repositories;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstname(String name);

    @Query("select c from Customer c")
    List<Customer> findByFirstnameLazy(String name);
    @Query("select c from Customer c join fetch c.address")
    List<Customer> findByFirstnameEager(String name);
}
