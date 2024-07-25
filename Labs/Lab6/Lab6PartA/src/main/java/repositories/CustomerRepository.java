package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    List<Customer> findBy();
    List<Customer> findByAddressZip(String zip);
    List<Customer> findByTheOrdersOrderlinesProductName(String name);
    
    //named query on Customer entity
    List<Customer> findCustomersFromCountry(@Param("country") String country);

    @Query("Select c.firstname + ' ' + c.lastname from Customer c Join c.address a Where a.city = :city")
    List<String> findCustomerInCity(@Param("city") String city);
}