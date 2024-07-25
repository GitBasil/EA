package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double price;
    private String category;
    private String supplier;

    public Product() {
    }

    public Product(String name, double price, String category, String supplier) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
