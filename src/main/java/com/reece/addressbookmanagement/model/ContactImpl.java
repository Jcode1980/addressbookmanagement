package com.reece.addressbookmanagement.model;

public class ContactImpl implements Contact {
    private String given;
    private String surname;
    private String phoneNumber;

    public ContactImpl(String given, String surname, String phoneNumber){
        this.given = given;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String given() {
        return given;
    }

    public String surname() {
        return surname;
    }

    public String phoneNumber(){return phoneNumber;}

    public String fullName(){
        StringBuilder fullName = new StringBuilder(given != null  ? given : "");
        fullName.append(surname != null ? surname : "");
        return fullName.toString();
    }

}
