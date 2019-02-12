package com.reece.addressbookmanagement;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static final Logger log = Logger.getLogger(Application.class);

    public static void main(String args[]){
        System.out.println("hello world");
        SpringApplication.run(Application.class, args);
    }

}

