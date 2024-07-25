package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Address;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
