package com.reece.addressbookmanagement.model;

import javax.persistence.*;

@Entity
@Table(name="contact")
public class Contact implements IContact{
    @Id
    @GeneratedValue
    private Long id;
    private String given;
    private String surname;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name="address_bookID")
    private AddressBook addressbook;

    public Contact() {
        super();
    }

    public Contact(String given, String surname, String phoneNumber){
        this.given = given;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressBook getAddressbook() {
        return addressbook;
    }

    public void setAddressbook(AddressBook addressbook) {
        this.addressbook = addressbook;
    }

    @Override
    public String toString() {
        return String.format("Contact [id=%s, name=%s, surname=%s, phoneNumber=%s]", id, getGiven(), getSurname(), getPhoneNumber());
    }


}
