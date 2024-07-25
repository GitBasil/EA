package Specifications;

import org.springframework.data.jpa.domain.Specification;

import domain.Customer;

public class CustomerSpecifications {

    public static Specification<Customer> CustomersLiveInCity(String city)
    {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("address").get("city"), city);
    }
    
}
