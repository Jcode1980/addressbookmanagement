package com.reece.addressbookmanagement.service;

import com.reece.addressbookmanagement.model.AddressBook;
import com.reece.addressbookmanagement.model.Contact;
import com.reece.addressbookmanagement.model.IAddressBook;
import com.reece.addressbookmanagement.model.IContact;

import java.util.Collection;
import java.util.List;

public interface IAddressBookService {
    Contact addContactToAddressBook(Long addressBookID, Contact contact);
    void deleteContact(Long contactID);
    Collection<Contact> retrieveAllContactsFromAddressBook(Long addressBookID);
    Collection<IContact> retrieveUniqieContactsFromAddressBooks(List<AddressBook> addressBooks);
    Contact getContact(Long contactID);
}