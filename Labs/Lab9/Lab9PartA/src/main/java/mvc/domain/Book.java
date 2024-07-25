package mvc.domain;

public class Book {
    private String isbn;
    private String author;
    private String title;
    private double price ;

    public Book() {
    }

    public Book(String isbn, String author, String title, double price) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.price = price;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
            " isbn='" + getIsbn() + "'" +
            ", author='" + getAuthor() + "'" +
            ", title='" + getTitle() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }
    
}
