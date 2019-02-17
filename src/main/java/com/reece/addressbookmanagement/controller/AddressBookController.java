package com.reece.addressbookmanagement.controller;

import com.reece.addressbookmanagement.DTO.ContactDto;
import com.reece.addressbookmanagement.model.Contact;
import com.reece.addressbookmanagement.service.IAddressBookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@RestController
@RequestMapping("/api/addressbook/")
public class AddressBookController {
    private Logger log = Logger.getLogger(AddressBookController.class);
    private IAddressBookService addressBookService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AddressBookController(IAddressBookService addressBookService){
        this.addressBookService = addressBookService;
    }

    /**
     * Returns all the contacts from the address book specified.
     *
     * @param addressBookID - the contact id to be looked up in the phone book.
     * @return the contact that matches request's id, if such contact exists.
     * @throws IllegalArgumentException if no address is found with this id.
     */
    @ApiOperation("Get a contacts for address book.")
    @GetMapping(value = "contacts/{addressBookID}", produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ContactDto> getContactsFromAddressBook(@ApiParam(value="The id of the address book which holds all the contacts")
                                                                 @PathVariable("addressBookID")String addressBookID)  {
        Collection<Contact> contactsFound = addressBookService.retrieveAllContactsFromAddressBook(Long.valueOf(addressBookID));
        System.out.println("found contacts");
        System.out.println(contactsFound);

        //return contactsFound;
        Collection<ContactDto> contactDtos = addressBookService.retrieveAllContactsFromAddressBook(Long.valueOf(addressBookID)).stream().
                map(contact-> modelMapper.map(contact, ContactDto.class)).collect(Collectors.toList());
        System.out.println(contactDtos);
        return contactDtos;
    }

    /**
     * Searches for all uniques contacts specified in the query. Query string
     * should contain all the ids of address books coma seperated e.g. (1, 2)
     *
     * @param addressBookIDsString - String of address book ids (comma seperated)
     * @return Unique list of contacts from the address books specified in the parameter passed in
     */
    @ApiOperation("Get all unique contacts from a list of address books.")
    @GetMapping(value = "contacts", produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ContactDto> getUniqueContactsFromAddressBooks(@ApiParam(value="A coma seperated String which contains address book IDs")
                                                                        @RequestParam("addressBookIDs") String addressBookIDsString)  {

        String[] addressBookStrings = addressBookIDsString.split(" *, *");
        Collection<Long> addressBookIDs = Arrays.stream(addressBookStrings).map(s -> Long.valueOf(s)).collect(Collectors.toList());
        log.info("these are the address book splits: " + addressBookIDs);

        Collection<ContactDto> contactsFound = addressBookService.retrieveUniqieContactsFromAddressBooks(addressBookIDs).stream().
                map(contact-> modelMapper.map(contact, ContactDto.class)).collect(Collectors.toList());
        System.out.println("found contacts");
        System.out.println(contactsFound);

        return contactsFound;
    }

    /**
     * Adds the contact into the address book.
     *
     * @param contactDto - the contact to be added in the phone book.
     * @return a <code>Contact</code>, that has been added to the address book
     * @throws IllegalArgumentException if no address is found with the specified ID
     */
    @ApiOperation("Add a contact to an Address book.")
    @PostMapping(value = "{addressBookID}/addContact", consumes="application/json", produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto addContactToAddressBook(
            @PathVariable("addressBookID")String addressBookID,
            @ApiParam(value="The contact to be added in the phone book") @RequestBody ContactDto contactDto) {
        log.info("this is the request body: " + contactDto);
        Contact contact =  modelMapper.map(contactDto, Contact.class);
        Contact savedContact = addressBookService.addContactToAddressBook(Long.valueOf(addressBookID), contact);
        return modelMapper.map(savedContact, ContactDto.class);
    }


    /**
     * Deletes the contact
     *
     * @param addressBookID - the id of the address book where the contact to be deleted resides.
     * @param contactId - the id of the contact to be deleted.
     * @throws IllegalArgumentException if no address is found with the specified addressBookID
     * @throws IllegalArgumentException if no contact is found with the specified contactID
     */
    @ApiOperation("Delete a contact from an address book.")
    @DeleteMapping(value="{addressBookID}/removeContact/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@ApiParam(value="The id of the address book") @PathVariable String addressBookID,
                              @ApiParam(value="The id of the contact to be deleted") @PathVariable String contactId) {
        addressBookService.deleteContactFromAddressBook(Long.valueOf(addressBookID), Long.valueOf(contactId));
    }


}
