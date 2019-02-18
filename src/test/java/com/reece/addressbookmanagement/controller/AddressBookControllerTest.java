package com.reece.addressbookmanagement.controller;

import com.reece.addressbookmanagement.DTO.ContactDto;
import com.reece.addressbookmanagement.config.AppConfig;
import com.reece.addressbookmanagement.model.AddressBook;
import com.reece.addressbookmanagement.model.Contact;
import com.reece.addressbookmanagement.service.AddressBookService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.mockito.BDDMockito.given;

import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class )
@WebMvcTest(AddressBookController.class)
public class AddressBookControllerTest {
    static private final String ADDRESS_BOOK_API_URL ="http://localhost:8080/api/addressbook/";
    private List<Contact> address1Contacts;
    private Contact newContact1;
    private Contact newContact2;

    private ContactDto newContact1Dto;
    private ContactDto newContact2Dto;



    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddressBookService addressBookService;

    @MockBean
    private ModelMapper modelMapper;


    private AddressBook addressBook;

    @Before
    public void setUp() throws Exception {
        newContact1 = new Contact("john", "Doe", "123525623");
        newContact2 = new Contact("Joe", "Harris", "675423432");

        newContact1Dto = new ContactDto("John", "Doe", "123525623");
        newContact2Dto = new ContactDto("Joe", "Harris", "675423432");
        address1Contacts = Arrays.asList(newContact1, newContact2);

        given(modelMapper.map(newContact1, ContactDto.class)).willReturn(newContact1Dto);
        given(modelMapper.map(newContact2, ContactDto.class)).willReturn(newContact2Dto);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContactsFromAddressBook() throws Exception{
        String contactsURL = ADDRESS_BOOK_API_URL+"contacts/1";

        System.out.println("contacts URL is: " + contactsURL);


        given(addressBookService.retrieveAllContactsFromAddressBook(1l)).willReturn(address1Contacts);


        //mvc.perform(get(ADDRESS_BOOK_API_URL+"contacts/1")
        mvc.perform(get(contactsURL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {System.out.println("Response: " + result.getResponse().getContentAsString());})
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].given", is("John")));
    }

    @Test
    public void getUniqueContactsFromAddressBooks() throws Exception{
        String uniqueContactsURL = ADDRESS_BOOK_API_URL+"contacts?addressBookIDs=1,2";

        //Contact newContact3 = new Contact("john", "Doe", "123525623");
        Contact newContact4 = new Contact("William", "Harris", "412345156");

        //ContactDto newContact3Dto = new ContactDto("John", "Doe", "123525623");
        ContactDto newContact4Dto = new ContactDto("William", "Harris", "412345156");
        List<Contact> uniqueAddressContacts = Arrays.asList(newContact1, newContact2, newContact4);

        given(addressBookService.retrieveUniqieContactsFromAddressBooks(Arrays.asList(1l, 2l))).willReturn(uniqueAddressContacts);
        given(modelMapper.map(newContact4, ContactDto.class)).willReturn(newContact4Dto);

        mvc.perform(get(uniqueContactsURL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {System.out.println("Response: " + result.getResponse().getContentAsString());})
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].given", is("John")))
                .andExpect(jsonPath("$[0].surname", is("Doe")))
                .andExpect(jsonPath("$[1].given", is("Joe")))
                .andExpect(jsonPath("$[1].surname", is("Harris")))
                .andExpect(jsonPath("$[2].given", is("William")))
                .andExpect(jsonPath("$[2].surname", is("Harris")));

    }

    @Test
    public void addContactToAddressBook() throws Exception{
        String addContactToAddressBook = ADDRESS_BOOK_API_URL+"1/addContact";
        Contact newContact = new Contact("David", "Shepparton", "123453432");
        ContactDto newContactDto = new ContactDto("David", "Shepparton", "123453432");
        given(addressBookService.addContactToAddressBook(1l, newContact)).willReturn(newContact);


        //log.info("this is the request body: " + newContactDto);
        given(modelMapper.map(newContact, ContactDto.class)).willReturn(newContactDto);

        Contact savedContact = addressBookService.addContactToAddressBook(1l, newContact);

        mvc.perform(post(addContactToAddressBook)
                .content(contactJsonObject("David", "Shepparton", "123453432").toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(result -> {System.out.println("Response: " + result.getResponse().getContentAsString());});
    }

    @Test
    public void deleteContact() throws Exception {
        String deleteContactFromAddressBookURL = ADDRESS_BOOK_API_URL+"1/removeContact/1";
        mvc.perform(delete(deleteContactFromAddressBookURL).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }


    private JSONObject contactJsonObject(String given, String surname, String phone) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("given", given);
        jsonObject.put("phone", phone);
        jsonObject.put("surname", surname);

        return jsonObject;
    }

}