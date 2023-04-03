package de.ithoc.training.addressbook.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {

    private String street;
    private String houseNo;
    private String postCode;
    private String city;
    private String country;

}
