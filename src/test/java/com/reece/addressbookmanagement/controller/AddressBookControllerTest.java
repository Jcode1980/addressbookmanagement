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

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.springframework.http.MediaType;


@RunWith(SpringRunner.class )
@ContextConfiguration(classes = { AppConfig.class })
@WebMvcTest(AddressBookController.class)
public class AddressBookControllerTest {

    static private final String ADDRESS_BOOK_API_URL ="http://localhost:8080/api/addressbook/";
    @Autowired
    private MockMvc mvc;

//    @MockBean
//    private AddressBookService addressBookService;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContactsFromAddressBook() throws Exception{
        String contactsURL = ADDRESS_BOOK_API_URL+"contacts/1";
        System.out.println("contacts URL is: " + contactsURL);
        //mvc.perform(get(ADDRESS_BOOK_API_URL+"contacts/1")
        mvc.perform(get(contactsURL)

                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {System.out.println("Response: " + result.getResponse().getContentAsString());});
                //.andExpect(jsonPath("$", hasSize(1)))
                //.andExpect(jsonPath("$[0].name", is("John")));
    }

//     mockMvc.perform(get(phoneBookUrl + "contacts").accept(contentType))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(contentType))
//            .andDo(result -> {System.out.println("Response: " + result.getResponse().getContentAsString());})
//            .andExpect(jsonPath("$", hasSize(jsonContacts.size())))
//            .andExpect(result -> jsonContacts.stream().forEach(contact -> jsonPath("$", hasItem(contact))));


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