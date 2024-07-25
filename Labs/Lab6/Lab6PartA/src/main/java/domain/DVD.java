package domain;

import javax.persistence.Entity;

@Entity
public class DVD extends Product
{
    String genre;

    public DVD() {
    }

    public DVD(String name, String description, double price, String genre) {
        super(name, description, price);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
			", name='" + getName() + "'" +
			", description='" + getDescription() + "'" +
			", price='" + getPrice() + "'" +
            " genre='" + getGenre() + "'" +
            "}";
    }

}