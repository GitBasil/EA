package Specifications;

import org.springframework.data.jpa.domain.Specification;

import domain.Order;

public class OrderSpecifications {

    public static Specification<Order> closedOrders()
    {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), "Closed");
    }
    
}
