package Lab4PartA.repositories.B;

import org.springframework.data.jpa.repository.JpaRepository;

import Lab4PartA.domain.B.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    public Publisher findFirstByName(String name);
}
