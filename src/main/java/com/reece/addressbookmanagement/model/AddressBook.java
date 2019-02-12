package com.reece.addressbookmanagement.model;

import java.util.List;

public interface AddressBook {
    List<Contact> contacts();

    boolean addToContacts();

    boolean deleteFromContacts();
}
