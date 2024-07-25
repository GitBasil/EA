package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine {
	@Id
	@GeneratedValue
	private long id;
	private int quantity;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Product product;

	public OrderLine() {
	}

	public OrderLine(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}
	
	public long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", quantity='" + getQuantity() + "'" +
			", product='" + getProduct() + "'" +
			"}";
	}

}
