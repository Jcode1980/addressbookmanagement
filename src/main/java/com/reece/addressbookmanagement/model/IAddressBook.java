package com.reece.addressbookmanagement.model;

import java.util.Collection;

public interface IAddressBook {
    Collection<Contact> getContacts();

    boolean addToContacts(Contact contact);

    boolean deleteFromContacts();
}
