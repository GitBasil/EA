package contacts.service;

import contacts.domain.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter {
    public static Contact getContactFromContactDTO(ContactDTO contactDTO){
        return new Contact(contactDTO.getFirstName(),
                contactDTO.getLastName(),
                contactDTO.getEmail(),
                contactDTO.getPhone());
    }
    public static ContactDTO getContactDTOFromContact(Contact contact){
      return new ContactDTO(contact.getFirstName(),
              contact.getLastName(),
              contact.getEmail(),
              contact.getPhone());
    }

    public static List<ContactDTO> getContactDTOsFromContacts(List<Contact> contacts){
        List<ContactDTO> contactDTOs = new ArrayList<ContactDTO>();
        for (Contact contact: contacts){
            contactDTOs.add(getContactDTOFromContact(contact));
        }
        return contactDTOs;
    }
}
