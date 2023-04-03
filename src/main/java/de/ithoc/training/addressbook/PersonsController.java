package de.ithoc.training.addressbook;

import de.ithoc.training.addressbook.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonsController {

    private final static List<Person> persons = new ArrayList<>();

    public PersonsController() {
        persons.add(new Person(12L, "Dr. Nice"));
        persons.add(new Person(13L, "Bombasto"));
        persons.add(new Person(14L, "Celeritas"));
        persons.add(new Person(15L, "Magneta"));
        persons.add(new Person(16L, "RubberMan"));
        persons.add(new Person(17L, "Dynama"));
        persons.add(new Person(18L, "Dr. IQ"));
        persons.add(new Person(19L, "Magma"));
        persons.add(new Person(20L, "Tornado"));
    }

    @GetMapping
    public @ResponseBody List<Person> get() {

        return persons;
    }

    @GetMapping("/{id}")
    public @ResponseBody Person getById(@PathVariable Long id) {

        return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst().
                orElse(null);
    }

    @PostMapping
    public @ResponseBody Person post(@RequestBody String name) {
        Long id = persons.get(persons.size()-1).getId();
        id = id + 1L;
        Person person = new Person(id, name);
        persons.add(person);

        return person;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody Void delete(@PathVariable Long id) {
        Optional<Person> first = persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
        first.ifPresent(persons::remove);

        return null;
    }

}
