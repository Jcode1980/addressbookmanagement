package com.reece.addressbookmanagement.DTO;

import com.reece.addressbookmanagement.model.Contact;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AddressBookDtoTest {
    @Test
    public void setContacts() {
        AddressBookDto addressBookDto = new AddressBookDto();
        Contact newContact1 = new Contact("john", "Doe", "123525623");
        Contact newContact2 = new Contact("Joe", "Harris", "675423432");
        List<Contact> contactsList = Arrays.asList(newContact1, newContact2);
        addressBookDto.setContacts(contactsList);
        assertThat(addressBookDto.getContacts(), is(contactsList));

    }
}