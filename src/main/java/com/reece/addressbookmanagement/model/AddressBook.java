package com.reece.addressbookmanagement.model;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.util.*;

@Entity
public class AddressBook implements IAddressBook {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "addressbook", fetch = FetchType.EAGER)
    private Collection<Contact> contacts;

    @Override
    public Collection<Contact> getContacts() { return contacts; }

    @Override
    public boolean addToContacts(Contact contact) {
        return false;
    }

    @Override
    public boolean deleteFromContacts() { return false; }



}
