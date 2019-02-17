package com.reece.addressbookmanagement.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ContactTest {
    private Contact contact;
    private AddressBook addressBook;
    static public final String GIVEN_NAME = "Naruto";
    static public final String SURNAME = "Uzomaki";
    static public final String PHONE_NUMBER = "23423400909";

    @Before
    public void setUp() throws Exception {
        contact = new Contact(GIVEN_NAME,SURNAME,PHONE_NUMBER);
        addressBook = new AddressBook("Anime Address Book");
        contact.setAddressbook(addressBook);
    }


    @After
    public void tearDown() throws Exception {
        contact = null;
    }


    @Test
    public void getGiven() {
        assertThat(contact.getGiven(), is(GIVEN_NAME));
    }

    @Test
    public void setGiven() {
        String given = "James";
        contact.setGiven(given);

        assertThat(contact.getGiven(), is(given));
    }

    @Test
    public void getSurname() {
        assertThat(contact.getGiven(), is(GIVEN_NAME));
    }

    @Test
    public void setSurname() {
        String surname = "Brown";
        contact.setSurname(surname);

        assertThat(contact.getSurname(), is(surname));
    }

    @Test
    public void getPhoneNumber() {
        assertThat(contact.getPhoneNumber(), is(PHONE_NUMBER));
    }

    @Test
    public void setPhoneNumber() {
        String phoneNumber = "65532237653";
        contact.setPhoneNumber(phoneNumber);

        assertThat(contact.getPhoneNumber(), is(phoneNumber));
    }

    @Test
    public void getAddressbook() {
        assertThat(contact.getAddressbook(), is(addressBook));
    }

    @Test
    public void setAddressbook() {
        AddressBook newAddressBook = new AddressBook("A new Address Book");
        contact.setAddressbook(newAddressBook);

        assertThat(contact.getAddressbook(), is(newAddressBook));
    }

    @Test
    public void toStringTest() {
        assertThat(contact.toString(), is(String.format(Contact.DISPLAY_FORMAT, contact.getId(),
                GIVEN_NAME, SURNAME, PHONE_NUMBER)));
    }

    @Test
    public void equals_shoulReturnTrueIfGivenSurnameAndPhoneAreTheSame() {
        Contact contactToCompare = new Contact(GIVEN_NAME, SURNAME, PHONE_NUMBER);

        assertThat(contact, is(contactToCompare));
    }

    @Test
    public void hashCode_shoulReturnTheSameValueIfGivenSurnameAndPhoneAreTheSame() {
        Contact contactToCompare = new Contact(GIVEN_NAME, SURNAME, PHONE_NUMBER);
        assertThat(contact.hashCode(), is(contactToCompare.hashCode()));

    }
}