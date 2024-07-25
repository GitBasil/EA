package bank.domain;

import javax.persistence.*;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private long id;
	private String name;

	public Customer() {
	}
	
	public Customer(String name) {
		this.name = name;
	}
	public Customer(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
