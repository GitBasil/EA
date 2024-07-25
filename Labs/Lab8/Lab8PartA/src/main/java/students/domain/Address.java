package students.domain;

public class Address {
    private String street;
    private String city;
    private String zip;

    public Address(String street, String city, String zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "{" +
            " street='" + this.street + "'" +
            ", city='" + this.city + "'" +
            ", zip='" + this.zip + "'" +
            "}";
    }
    
}
