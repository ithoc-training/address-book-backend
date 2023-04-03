package de.ithoc.training.addressbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
public class Person {

    @Setter
    private Long id;

    private String firstName;
    private String lastName;
    private List<Address> addresses;

    public Person(Long id, String firstName, String lastName, List<Address> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }

    public Person(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, List<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }

}
