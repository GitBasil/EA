package contacts.service;

import contacts.repositories.ContactRepository;
import contacts.domain.Contact;
import contacts.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    EmailSender emailSender;

    public void add(ContactDTO contactDTO){
        Contact contact = ContactAdapter.getContactFromContactDTO(contactDTO);
        contactRepository.save(contact);
        emailSender.sendEmail(contact.getEmail(), "Welcome");
    }

    public void update(ContactDTO contactDTO){
        Contact contact = ContactAdapter.getContactFromContactDTO(contactDTO);
        contactRepository.save(contact);
    }

    public ContactDTO findByFirstName(String firstName){
        Contact contact = contactRepository.findByFirstName(firstName);
        return ContactAdapter.getContactDTOFromContact(contact);
    }

    public void delete(String firstName){
        Contact contact = contactRepository.findByFirstName(firstName);
        emailSender.sendEmail(contact.getEmail(), "Good By");
        contactRepository.delete(contact);
    }

    public Collection<ContactDTO> findAll(){
        return ContactAdapter.getContactDTOsFromContacts(contactRepository.findAll());
    }
}
