package PersonPet.repositories.SQL;

import org.springframework.data.jpa.repository.JpaRepository;

import PersonPet.domain.SQL.PersonSQL;

public interface PersonRepositorySQL extends JpaRepository<PersonSQL,Long> {
    
}
