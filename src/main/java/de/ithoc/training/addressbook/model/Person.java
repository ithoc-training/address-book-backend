package de.ithoc.training.addressbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Person {

    private String id;

    private String firstName;
    private String lastName;
    private List<Address> addresses;

}
