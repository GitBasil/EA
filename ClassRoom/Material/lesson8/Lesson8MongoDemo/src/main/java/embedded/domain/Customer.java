package embedded.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document
public class Customer {
	@Id
	private int customerNumber;
	private String name;
	private String email;
	private String phone;

	private Address address;
	private List<CreditCard> creditCards = new ArrayList<>();

	public Customer(int customerNumber, String name, String email, String phone) {
		this.customerNumber = customerNumber;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}



	public int getCustomerNumber() {
		return customerNumber;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void addCreditCard(CreditCard creditCard) {
		creditCards.add(creditCard);
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerNumber=" + customerNumber +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", address=" + address +
				", creditCards=" + creditCards +
				'}';
	}
}