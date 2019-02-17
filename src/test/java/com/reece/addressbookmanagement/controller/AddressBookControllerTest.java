package com.reece.addressbookmanagement.controller;

import com.reece.addressbookmanagement.config.AppConfig;
import com.reece.addressbookmanagement.service.AddressBookService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class )
@ContextConfiguration(classes = { AppConfig.class })
@WebMvcTest(AddressBookController.class)
public class AddressBookControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddressBookService addressBookService;


    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContactsFromAddressBook() {
    }

    @Test
    public void getUniqueContactsFromAddressBooks() {
    }

    @Test
    public void addContactToAddressBook() {
    }

    @Test
    public void deleteContact() {
    }
}