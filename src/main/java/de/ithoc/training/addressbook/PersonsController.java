package de.ithoc.training.addressbook;

import de.ithoc.training.addressbook.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
@CrossOrigin
public class PersonsController {

    private final static List<Person> persons = new ArrayList<>();

    public PersonsController() {
        persons.add(new Person(12L, "Dr.", "Nice", List.of()));
        persons.add(new Person(13L, "Bom", "Basto", List.of()));
        persons.add(new Person(14L, "Cele", "Ritas", List.of()));
        persons.add(new Person(15L, "Ma", "Gneta", List.of()));
        persons.add(new Person(16L, "Rubber", "Man", List.of()));
        persons.add(new Person(17L, "Dy", "Nama", List.of()));
        persons.add(new Person(18L, "Dr.", "IQ", List.of()));
        persons.add(new Person(19L, "Mag", "Ma", List.of()));
        persons.add(new Person(20L, "Tor", "Nado", List.of()));
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
    public @ResponseBody Person post(@RequestBody Person person) {
        person.setId(maxId() + 1);
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

    @PutMapping
    public @ResponseBody Person put(@RequestBody Person person) {
        Optional<Person> personToUpdate = persons.stream()
                .filter(p -> p.getId().equals(person.getId()))
                .findFirst();
        if(personToUpdate.isPresent()) {
            persons.remove(personToUpdate.get());
            persons.add(person);
        }

        return person;
    }


    private Long maxId() {
        return persons.stream()
                .map(Person::getId)
                .reduce(Long.MIN_VALUE, Long::max);
    }

}
