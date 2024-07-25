package domain;

import javax.persistence.*;

@Entity
public class Book extends Product
{
    String isbn;

    public Book() {
    }

    public Book(String name, String description, double price, String isbn) {
        super(name, description, price);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
			", name='" + getName() + "'" +
			", description='" + getDescription() + "'" +
			", price='" + getPrice() + "'" +
            " isbn='" + getIsbn() + "'" +
            "}";
    }
    

}