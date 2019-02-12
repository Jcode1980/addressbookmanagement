package com.reece.addressbookmanagement.model;

import java.util.List;

public interface IContact {
    String given();
    String surname();

    List<ContactNumber> contactNumbers();

}
