package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.OrderLine;


public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
