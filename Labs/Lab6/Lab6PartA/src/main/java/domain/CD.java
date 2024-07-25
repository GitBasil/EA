package domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "CD.findByArtist", query = "SELECT cd from CD cd WHERE cd.artist = :artist")
public class CD extends Product
{
    String artist;

    public CD() {
    }

    public CD(String name, String description, double price, String artist) {
        super(name, description, price);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
			", name='" + getName() + "'" +
			", description='" + getDescription() + "'" +
			", price='" + getPrice() + "'" +
            " artist='" + getArtist() + "'" +
            "}";
    }

}