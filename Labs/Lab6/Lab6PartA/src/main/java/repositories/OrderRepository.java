package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Order;


public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @Query("SELECT o.ordernr from Order o Where o.status = 'Closed'")
    List<String> allClosedOrders();

    @Query("SELECT o.ordernr from Order o Join o.customer c Join c.address a Where a.city = :city")
    List<String> allOrdersFromCity(@Param("city") String city);
}
