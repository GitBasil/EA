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

    @OneToMany (cascade={CascadeType.PERSIST}, fetch=FetchType.EAGER)
    private Collection<CreditCard> creditcards=new ArrayList<CreditCard>();

    
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

	public Collection<CreditCard> getCreditcard() {
		return creditcards;
	}

	public void setCreditcard(Collection<CreditCard> creditcard) {
		this.creditcards = creditcard;
	}


	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", creditcards=" + creditcards +
				'}';
	}
}
