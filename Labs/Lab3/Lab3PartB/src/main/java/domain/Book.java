package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String ISBN;
	private String author;
	private Double price;

	// this constructor is for the SpringBoot to use when using the JPA
	protected Book() {
	}

	// this constructor is only for you so you can create a book
	public Book(String title, String ISBN, String author, Double price) {
		super();
		this.title = title;
		this.ISBN = ISBN;
		this.author = author;
		this.price = price;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return String.format("Book[id=%d, title='%s', ISBN='%s', author='%s', price='%s']", id, title, ISBN, author, price);
	}

}


