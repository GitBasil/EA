package domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Customer {
	@Id
	@GeneratedValue
	private long id;
	private String firstname;
    private String lastname;


    @ManyToOne(cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
    private Address address;
    
    public Customer(){
    }
    public Customer(String firstname,String lastname){
    	this.firstname=firstname;
    	this.lastname=lastname;

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

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
		//		", address=" + address +
				'}';
	}
}
