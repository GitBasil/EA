package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
