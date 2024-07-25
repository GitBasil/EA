package Lab4PartA.repositories.D;

import org.springframework.data.jpa.repository.JpaRepository;

import Lab4PartA.domain.D.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
    public School findFirstByName(String name);
}
