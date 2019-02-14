package com.reece.addressbookmanagement.service;

import com.reece.addressbookmanagement.model.AddressBook;
import com.reece.addressbookmanagement.model.Contact;
import com.reece.addressbookmanagement.model.IAddressBook;
import com.reece.addressbookmanagement.model.IContact;
import com.reece.addressbookmanagement.repository.AddressBookRepository;
import com.reece.addressbookmanagement.repository.ContactRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AddressBookService implements IAddressBookService {
    private Logger log = Logger.getLogger(AddressBookService.class);
    private AddressBookRepository addressBookRepository;
    private ContactRepository contactRepository;

    @Autowired
    public AddressBookService(AddressBookRepository addressBookRepository, ContactRepository contactRepository){
        this.addressBookRepository = addressBookRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact addContactToAddressBook(Long addressBookID, Contact contact) {
        AddressBook addressBook = addressBookRepository.findById(addressBookID).orElseThrow(()->new IllegalArgumentException("id not found"));
        contact.setAddressbook(addressBook);
        System.out.println("trying to save contact: " + contact);
        //Contact anotherContact = new Contact("a", "b", "12312312");
        Contact savedContact = contactRepository.save(contact);
        return savedContact;
    }

    @Override
    public void deleteContactFromAddressBook(Long addressBookID, Long contactID) {
        AddressBook addressBook = addressBookRepository.findById(addressBookID).orElseThrow(()->new IllegalArgumentException("id not found"));

        Contact foundContact = addressBook.getContacts().stream().filter(contact -> contact.getId()
                .equals(contactID)).findFirst().orElseThrow(()->new IllegalArgumentException("id not found"));

        contactRepository.delete(foundContact);
    }


    @Override
    public Collection<Contact> retrieveAllContactsFromAddressBook(Long addressBookID){
        log.info("got to retrieveAllContactsFromAddressBook ");
        AddressBook addressBook = addressBookRepository.findById(addressBookID).orElseThrow(()->new IllegalArgumentException("id not found"));
        return addressBook.getContacts();
    }

    @Override
    public Collection<Contact> retrieveUniqieContactsFromAddressBooks(Collection<Long> addressBookIDs) {
        log.info("got to retrieveUniqieContactsFromAddressBooks ");
        List<AddressBook> addressBooks = addressBookRepository.findAllById(addressBookIDs);

        return allUniqueContactsForAddressBooks(addressBooks);
    }

    @Override
    public Contact getContact(Long contactID){
        return contactRepository.findById(contactID).orElseThrow(()->new IllegalArgumentException());
    }

    //FIXME
    private List<Contact> allUniqueContactsForAddressBooks(List<AddressBook> addressBooks){
        //implement me
        return null;
    }
}
