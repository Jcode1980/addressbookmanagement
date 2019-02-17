package com.reece.addressbookmanagement.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class AddressBookTest {
    private AddressBook addressBook;

    @Before
    public void setUp() throws Exception {
        addressBook = new AddressBook("New Address Book");

        Contact newContact1 = new Contact("john", "Doe", "123525623");
        Contact newContact2 = new Contact("Joe", "Harris", "675423432");
        addressBook.setContacts(Arrays.asList(newContact1, newContact2));

    }


    @After
    public void tearDown() throws Exception {
        addressBook = null;
    }

    @Test
    public void getContacts() {
        assertThat(addressBook.getContacts().size(), is(2));
    }

    @Test
    public void setContacts(){
        Contact newContact1 = new Contact("James", "Dean", "43234235323");
        Contact newContact2 = new Contact("Rob", "Harris", "64552332332");
        Contact newContact3 = new Contact("Christain", "Bale", "43242363234234");
        List contactsList = Arrays.asList(newContact1, newContact2, newContact3);
        addressBook.setContacts(contactsList);
        assertThat(addressBook.getContacts(), is(contactsList));


    }
}