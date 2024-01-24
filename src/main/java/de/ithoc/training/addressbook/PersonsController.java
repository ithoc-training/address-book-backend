package de.ithoc.training.addressbook;

import de.ithoc.training.addressbook.model.Person;
import de.ithoc.training.addressbook.repository.PersonEntity;
import de.ithoc.training.addressbook.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
@CrossOrigin
public class PersonsController {

    private final PersonRepository personRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public PersonsController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public @ResponseBody List<Person> get() {

        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(personEntity -> {
            Person person = modelMapper.map(personEntity, Person.class);
            persons.add(person);
        });

        return persons;
    }

    @GetMapping("/{id}")
    public @ResponseBody Person getById(@PathVariable String id) {

        Optional<PersonEntity> personEntityOptional = personRepository.findById(UUID.fromString(id));
        if (personEntityOptional.isPresent()) {
            PersonEntity personEntity = personEntityOptional.get();
            return modelMapper.map(personEntity, Person.class);
        }

        return null;
    }

    @PostMapping
    public @ResponseBody Person post(@RequestBody Person person) {

        PersonEntity personEntity = modelMapper.map(person, PersonEntity.class);
        personRepository.save(personEntity);

        return modelMapper.map(personEntity, Person.class);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody Void delete(@PathVariable String id) {

        personRepository.deleteById(UUID.fromString(id));

        return null;
    }

    @PutMapping
    public @ResponseBody Person put(@RequestBody Person person) {

        personRepository.save(modelMapper.map(person, PersonEntity.class));

        return person;
    }

}
