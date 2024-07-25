package bank.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import bank.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Long> {
}