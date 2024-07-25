package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue
	private long id;
	private String country;
	private String street;
	private String city;
	private String zip;

	public Address() {
	}
	
	public Address(String country, String street, String city, String zip) {
		super();
		this.country = country;
		this.street = street;
		this.city = city;
		this.zip = zip;
	}
	public long getId() {
		return id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}


	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", country='" + getCountry() + "'" +
			", street='" + getStreet() + "'" +
			", city='" + getCity() + "'" +
			", zip='" + getZip() + "'" +
			"}";
	}
	

}
