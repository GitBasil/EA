package mvc.domain;

import java.util.ArrayList;
import java.util.List;

public class Books {
    private List<Book> books = new ArrayList<>();

    public Books() {
    }

    public Books(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "{" +
            " books='" + getBooks() + "'" +
            "}";
    }

}
