package com.reece.addressbookmanagement.controller;

import com.reece.addressbookmanagement.model.ContactImpl;
import com.reece.addressbookmanagement.service.AddressBookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/AddressBook")
public class AddressBookController {
    private Logger log = Logger.getLogger(AddressBookController.class);
    private AddressBookService addressBookService;

    @Autowired
    public AddressBookController(AddressBookService addressBookService){
        this.addressBookService = addressBookService;
    }

    @GetMapping("/contacts")
    public List<ContactImpl> getContacts(){
        log.info("got to get COntacts function");
        return new ArrayList<>();
    }


}
