package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
