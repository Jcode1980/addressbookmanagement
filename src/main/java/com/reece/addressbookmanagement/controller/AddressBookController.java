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
@RequestMapping("/api")
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
     * Searches in phonebook for a contact that maches the GET reqeust's id and if such a contact exists, returns it.
     *
     * @param addressBookID - the contact id to be looked up in the phone book.
     * @return the contact that matches request's id, if such contact exists.
     * @throws IllegalArgumentException if no contact is found with this id.
     */
    @ApiOperation("Get a contacts for address book.")
    @GetMapping(value = "/addressBook/contacts/{addressBookID}", produces="application/json")
    public Collection<ContactDto> getContactsFromAddressBook(@ApiParam(value="The id of the address book which holds all the contacts")
                                                                 @PathVariable("addressBookID")String addressBookID) throws IllegalArgumentException {
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
     * Searches in phonebook for a contact that maches the GET reqeust's id and if such a contact exists, returns it.
     *
     * @param addressBookIDsString - String of address book ids (comma seperated)
     * @return Unique list of contacts from the address books specified in the parameter passed in
     */
    @ApiOperation("Get all unique contacts from a list of address books.")
    @GetMapping(value = "/addressBook/contacts", produces="application/json")
    public Collection<ContactDto> getUniqueContactsFromAddressBooks(@ApiParam(value="A String which contains address book IDs")
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
     * Adds the contact included in the request body to the phone book. The {@link Contact} provided by the client
     * will contain no indexing, but just the name, surname and phone details. It does not matter if there is already
     * another client with the same details. The new client will be provided with a unique id and be added in
     * the phone book.
     *
     * @param contactDto - the contact to be added in the phone book.
     * @return an {@link Contact}, which has the same details with the contact provided, but also the unique id that identifies it in the phone book.
     */
    @ApiOperation("Add a contact to an Address book.")
    @PostMapping(value = "/addressBook/{addressBookID}/addContact", consumes="application/json", produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto addContactToAddressBook(
            @PathVariable("addressBookID")String addressBookID,
            @ApiParam(value="The contact to be added in the phone book") @RequestBody ContactDto contactDto) {
        System.out.println("this is the request body: " + contactDto);
        Contact contact =  modelMapper.map(contactDto, Contact.class);
        Contact savedContact = addressBookService.addContactToAddressBook(Long.valueOf(addressBookID), contact);
        return modelMapper.map(savedContact, ContactDto.class);
    }


    /**
     * Deletes the contact under the specific URI.
     *
     * @param id - the id of the contact to be deleted.
     */
    @ApiOperation("Delete a contact from the phone book using its id.")
    @DeleteMapping(value="/addressBook/{addressBookID}/removeContact/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@ApiParam(value="The id of the contact to be deleted") @PathVariable String id) {
        //addressBookService.deleteContactFromAddressBook(Long.valueOf(id));
    }


    /**
     * Searches in phonebook for a contact that maches the GET reqeust's id and if such a contact exists, returns it.
     *
     * @param id - the contact id to be looked up in the phone book.
     * @return the contact that matches request's id, if such contact exists.
     */
    @ApiOperation("Get a single contact using its id.")
    @RequestMapping(value="/contact/{id}", method=RequestMethod.GET, produces="application/json")
    public ContactDto getContact(@ApiParam(value="The id of the contact to be retrieved") @PathVariable String id) {
        return modelMapper.map(addressBookService.getContact(Long.valueOf(id)), ContactDto.class);
    }




//    private ContactDto convertToDto(Contact contact) {
//        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);
//
//        return postDto;
//    }

//    private Contact convertToEntity(ContactDto postDto) throws ParseException {
//        Post post = modelMapper.map(postDto, Post.class);
//        post.setSubmissionDate(postDto.getSubmissionDateConverted(
//                userService.getCurrentUser().getPreference().getTimezone()));
//
//        if (postDto.getId() != null) {
//            Post oldPost = contactRe.getPostById(postDto.getId());
//            post.setRedditID(oldPost.getRedditID());
//            post.setSent(oldPost.isSent());
//        }
//        return post;
//    }



}
