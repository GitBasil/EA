package Lab4PartA.domain.B;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Publisher {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<Book>();

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books='" + books + '\'' +
                '}';
    }
}