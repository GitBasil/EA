package Lab4PartA.repositories.B;

import org.springframework.data.jpa.repository.JpaRepository;

import Lab4PartA.domain.B.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findFirstByisbn(String isbn);
}
