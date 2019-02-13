package com.reece.addressbookmanagement.service;

import com.reece.addressbookmanagement.model.AddressBook;
import com.reece.addressbookmanagement.model.Contact;
import com.reece.addressbookmanagement.model.IAddressBook;
import com.reece.addressbookmanagement.model.IContact;
import com.reece.addressbookmanagement.repository.AddressBookRepository;
import com.reece.addressbookmanagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AddressBookService implements IAddressBookService {
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
    public void deleteContact(Long contactID) {
        contactRepository.deleteById(contactID);
    }

    @Override
    public Collection<Contact> retrieveAllContactsFromAddressBook(Long addressBookID) {
        System.out.println("got to retrieveAllContactsFromAddressBook ");
        AddressBook addressBook = addressBookRepository.findById(addressBookID).orElseThrow(()->new IllegalArgumentException("id not found"));
        return addressBook.getContacts();
    }

    @Override
    public Collection<IContact> retrieveUniqieContactsFromAddressBooks(List<AddressBook> addressBooks) {
        System.out.println("got to retrieveUniqieContactsFromAddressBooks ");
        return null;
    }

    @Override
    public Contact getContact(Long contactID){
        return contactRepository.findById(contactID).orElseThrow(()->new IllegalArgumentException());
    }
}
