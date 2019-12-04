package com.example.demo2;

import com.example.demo2.models.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Optional;

@Controller
public class NameExchangeController {

    private Person[] persons = new Person[]{
            new Person("Petr", "Kmoch"),
            new Person("Jan", "Novak")
    };


    @GetMapping(value = "/person")
    public ResponseEntity<Person> readPersonByName(@RequestParam String name) {
        final Optional<Person> first = Arrays.stream(persons).filter(one -> one.getName().equals(name)).findFirst();
        return new ResponseEntity<Person>(first.isPresent() ? first.get() : new Person(), HttpStatus.OK);
    }


}
