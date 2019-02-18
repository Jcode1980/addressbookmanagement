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

import java.util.*;
import java.util.stream.Collectors;

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
        AddressBook addressBook = addressBookRepository.findById(addressBookID).
                orElseThrow(()->new IllegalArgumentException("Address with id " +addressBookID+" not found."));

        Contact foundContact = addressBook.getContacts().stream().filter(contact -> contact.getId()
                .equals(contactID)).findFirst().orElseThrow(() ->
                new IllegalArgumentException("Contact with id " +contactID+" not found in address book."));

        contactRepository.delete(foundContact);
    }

    @Override
    public List<Contact> retrieveAllContactsFromAddressBook(Long addressBookID){
        log.info("got to retrieveAllContactsFromAddressBook ");
        AddressBook addressBook = addressBookRepository.findById(addressBookID).
                orElseThrow(()->new IllegalArgumentException("Address with id " +addressBookID+" not found."));
        return addressBook.getContacts();
    }

    @Override
    public List<Contact> retrieveUniqieContactsFromAddressBooks(Collection<Long> addressBookIDs) {
        log.info("got to retrieveUniqieContactsFromAddressBooks ");
        List<AddressBook> addressBooks = addressBookRepository.findAllById(addressBookIDs);

        return allUniqueContactsForAddressBooks(addressBooks);
    }


    private List<Contact> allUniqueContactsForAddressBooks(List<AddressBook> addressBooks){
        HashSet<Contact> uniqueContacts = new HashSet<>();
        addressBooks.stream().forEach(addressBook -> uniqueContacts.addAll(addressBook.getContacts()));
        //ArrayList<Contact> contacts = new ArrayList<>(uniqueContacts);
        Comparator<Contact> nameComparator = (h1, h2) -> h1.getId().compareTo(h2.getId());
        List<Contact> sortedContacts = uniqueContacts.stream().sorted(nameComparator).collect(Collectors.toList());

        return  sortedContacts;
    }
}
