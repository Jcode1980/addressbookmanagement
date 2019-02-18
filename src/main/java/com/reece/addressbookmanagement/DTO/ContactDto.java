package com.reece.addressbookmanagement.DTO;


public class ContactDto {
    private Long id;
    private String given;
    private String surname;
    private String phoneNumber;

    @SuppressWarnings("WeakerAccess")
    public ContactDto() {
        super();
    }

    public ContactDto(String given, String surname, String phoneNumber){
        this.given = given;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
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


    public String getPhoneNumber(){return phoneNumber;}

    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return String.format("Contact [id=%s, given=%s, surname=%s, phoneNumber=%s]", id, getGiven(), getSurname(), getPhoneNumber());
    }
}
