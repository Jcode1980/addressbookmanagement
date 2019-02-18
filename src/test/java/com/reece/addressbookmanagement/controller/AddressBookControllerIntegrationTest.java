package com.reece.addressbookmanagement.controller;

import com.reece.addressbookmanagement.Application;
import com.reece.addressbookmanagement.DTO.ContactDto;
import com.reece.addressbookmanagement.model.Contact;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class AddressBookControllerIntegrationTest {
    static private final String ADDRESS_BOOK_API_URL ="http://localhost:8080/api/addressbook/";

    @Autowired
    private MockMvc mvc;

    @Test
    public void getContactsFromAddressBook_shouldReturnCorrectContacts() throws Exception {
        String contactsURL = ADDRESS_BOOK_API_URL + "contacts/1";

        System.out.println("contacts URL is: " + contactsURL);

        mvc.perform(get(contactsURL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println("getContactsFromAddressBook Response: " + result.getResponse().getContentAsString());
                })
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].given", is("Kevin")))
                .andExpect(jsonPath("$[0].surname", is("Durant")))
                .andExpect(jsonPath("$[0].phoneNumber", is("0141532123")))
                .andExpect(jsonPath("$[1].given", is("Steve")))
                .andExpect(jsonPath("$[1].surname", is("Nash")))
                .andExpect(jsonPath("$[1].phoneNumber", is("11234426553")))
                .andExpect(jsonPath("$[2].given", is("Lebron")))
                .andExpect(jsonPath("$[2].surname", is("James")))
                .andExpect(jsonPath("$[2].phoneNumber", is("42343255234")));
    }

    @Test
    public void getUniqueContactsFromAddressBooks_shouldOnlyReturnUniqueContacts() throws Exception{
        String uniqueContactsURL = ADDRESS_BOOK_API_URL+"contacts?addressBookIDs=1,2";

        mvc.perform(get(uniqueContactsURL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {System.out.println("getUniqueContactsFromAddressBooks Response: " + result.getResponse().getContentAsString());})
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].given", is("Kevin")))
                .andExpect(jsonPath("$[0].surname", is("Durant")))
                .andExpect(jsonPath("$[0].phoneNumber", is("0141532123")))
                .andExpect(jsonPath("$[1].given", is("Steve")))
                .andExpect(jsonPath("$[1].surname", is("Nash")))
                .andExpect(jsonPath("$[1].phoneNumber", is("11234426553")))
                .andExpect(jsonPath("$[2].given", is("Lebron")))
                .andExpect(jsonPath("$[2].surname", is("James")))
                .andExpect(jsonPath("$[2].phoneNumber", is("42343255234")))
                .andExpect(jsonPath("$[3].given", is("Naruto")))
                .andExpect(jsonPath("$[3].surname", is("Ozimaki")))
                .andExpect(jsonPath("$[3].phoneNumber", is("0141542101")))
                .andExpect(jsonPath("$[4].given", is("Sasake")))
                .andExpect(jsonPath("$[4].surname", is("Uchiha")))
                .andExpect(jsonPath("$[4].phoneNumber", is("0141542101")));


    }



    @Test
    public void addContactToAddressBook_shouldAddToCorrectAddressBook() throws Exception{
        String contactsURL = ADDRESS_BOOK_API_URL + "contacts/1";
        String addContactToAddressBook = ADDRESS_BOOK_API_URL+"1/addContact";

        mvc.perform(post(addContactToAddressBook)
                .content(contactJsonObject("Paul", "Pierce", "123453432").toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(result -> {System.out.println("Response: " + result.getResponse().getContentAsString());});

        mvc.perform(get(contactsURL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println("Response: " + result.getResponse().getContentAsString());
                })
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].given", is("Paul")))
                .andExpect(jsonPath("$[0].surname", is("Pierce")))
                .andExpect(jsonPath("$[0].phoneNumber", is("123453432")))
                .andExpect(jsonPath("$[1].given", is("Kevin")))
                .andExpect(jsonPath("$[1].surname", is("Durant")))
                .andExpect(jsonPath("$[1].phoneNumber", is("0141532123")))
                .andExpect(jsonPath("$[2].given", is("Steve")))
                .andExpect(jsonPath("$[2].surname", is("Nash")))
                .andExpect(jsonPath("$[2].phoneNumber", is("11234426553")))
                .andExpect(jsonPath("$[3].given", is("Lebron")))
                .andExpect(jsonPath("$[3].surname", is("James")))
                .andExpect(jsonPath("$[3].phoneNumber", is("42343255234")));
    }

    @Test
    public void addContactToAddressBook_shouldThrowExceptionWhenAddressBookIsNotFound() throws Exception {
        String addContactToAddressBook = ADDRESS_BOOK_API_URL + "10/addContact";

        mvc.perform(post(addContactToAddressBook)
                .content(contactJsonObject("Paul", "Pierce", "123453432").toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deleteContact() throws Exception {
        String deleteContactFromAddressBookURL = ADDRESS_BOOK_API_URL+"1/removeContact/100001";
        String contactsURL = ADDRESS_BOOK_API_URL + "contacts/1";


        mvc.perform(delete(deleteContactFromAddressBookURL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get(contactsURL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println("Response: " + result.getResponse().getContentAsString());
                })
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].given", is("Steve")))
                .andExpect(jsonPath("$[0].surname", is("Nash")))
                .andExpect(jsonPath("$[0].phoneNumber", is("11234426553")))
                .andExpect(jsonPath("$[1].given", is("Lebron")))
                .andExpect(jsonPath("$[1].surname", is("James")))
                .andExpect(jsonPath("$[1].phoneNumber", is("42343255234")));
    }

    @Test
    public void deleteContact_shouldThrowExceptionWhenContactNotFound() throws Exception {
        String deleteContactFromAddressBookURL = ADDRESS_BOOK_API_URL+"1/removeContact/1001000";

        mvc.perform(delete(deleteContactFromAddressBookURL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deleteContact_shouldThrowExceptionWhenAddressNotFound() throws Exception {
        String deleteContactFromAddressBookURL = ADDRESS_BOOK_API_URL+"3/removeContact/100001";

        mvc.perform(delete(deleteContactFromAddressBookURL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    private JSONObject contactJsonObject(String given, String surname, String phone) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("given", given);
        jsonObject.put("phoneNumber", phone);
        jsonObject.put("surname", surname);

        return jsonObject;
    }


}


