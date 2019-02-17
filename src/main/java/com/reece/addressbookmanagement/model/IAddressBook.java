package com.reece.addressbookmanagement.model;

import java.util.Collection;
import java.util.List;

public interface IAddressBook {
    Collection<Contact> getContacts();

    void setContacts(List<Contact> contacts);

    String getName();

    void setName(String name);

}
