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
    private String name;

    @OneToMany(mappedBy = "addressbook", fetch = FetchType.EAGER)
    private List<Contact> contacts;

    public AddressBook(){}

    public AddressBook(String name){this.name = name;}

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) { this.name = name; }

    @Override
    public List<Contact> getContacts() {
        if(contacts == null){
            contacts = new ArrayList<>();
        }
        return contacts;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }


}
