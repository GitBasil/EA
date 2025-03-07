package bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import bank.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}