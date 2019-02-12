package com.reece.addressbookmanagement.service;

import com.reece.addressbookmanagement.model.AddressBook;
import com.reece.addressbookmanagement.model.Contact;

import java.util.List;

public interface AddressBookService {
    boolean addContactToAddressBook(AddressBook book, Contact contact);
    boolean removeContactFromAddressBook(AddressBook book, Contact contact);
    List<Contact> retrieveAllContactsFromAddressBook(AddressBook addressBook);
    List<Contact> retrieveUniqieContactsFromAddressBooks(List<AddressBook> addressBooks);
}
