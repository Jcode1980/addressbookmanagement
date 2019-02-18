package com.reece.addressbookmanagement.DTO;

import com.reece.addressbookmanagement.model.Contact;
import java.util.Collection;

public class AddressBookDto {

    private Collection<Contact> contacts;

    public Collection<Contact> getContacts() { return contacts; }

    public void setContacts(Collection<Contact> contacts){this.contacts = contacts;}

}
