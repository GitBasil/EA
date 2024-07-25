package contacts.repositories;

import contacts.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Long> {
    public Contact findByFirstName(String firstName);
}

