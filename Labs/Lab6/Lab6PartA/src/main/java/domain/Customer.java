package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Customer.findCustomersFromCountry", query = "SELECT c FROM Customer c Join c.address a WHERE a.country = :country")
public class Customer {
	@Id
	@GeneratedValue
	private long id;
	private String firstname;
	private String lastname;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address address;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Collection<Order> theOrders = new ArrayList<Order>();

	public Customer() {
	}

	public Customer(String firstname, String lastname, String country, String street,
			String city, String zip) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = new Address(country, street, city, zip);
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getAddress() {
		return address;
	}



	public Collection<Order> getTheOrders() {
		return Collections.unmodifiableCollection(theOrders);
	}

	
	public boolean addOrder(Order order) {
		boolean added = theOrders.add(order); 
		if (added) {
			order.setCustomer(this);
		}
		return added;
	}

	public boolean removeOrder(Order order) {
		boolean removed = theOrders.remove(order);
		if (removed) {
			theOrders.remove(order);
		}
		return removed;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", address=" + address +
				", theOrders=" + theOrders +
				'}';
	}
}
