package com.reece.addressbookmanagement;

import com.reece.addressbookmanagement.model.Contact;
import com.reece.addressbookmanagement.repository.ContactRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String args[]){
        System.out.println("hello world");
        SpringApplication.run(Application.class, args);
    }

}


