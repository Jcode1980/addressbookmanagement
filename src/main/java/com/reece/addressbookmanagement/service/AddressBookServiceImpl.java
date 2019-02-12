package com.reece.addressbookmanagement.service;

import com.reece.addressbookmanagement.model.AddressBook;
import com.reece.addressbookmanagement.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Override
    public boolean addContactToAddressBook(AddressBook book, Contact contact) {
        return false;
    }

    @Override
    public boolean removeContactFromAddressBook(AddressBook book, Contact contact) {
        return false;
    }

    @Override
    public List<Contact> retrieveAllContactsFromAddressBook(AddressBook addressBook) {
        return null;
    }

    @Override
    public List<Contact> retrieveUniqieContactsFromAddressBooks(List<AddressBook> addressBooks) {
        return null;
    }
}
