package com.jj.criteriaApiDemo.controller;

import com.jj.criteriaApiDemo.entity.Person;
import com.jj.criteriaApiDemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/getAll")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/getAllByName/{name}")
    public List<Person> getAllByName(@PathVariable String name) {
        return personService.getAllByName(name);
    }

    @GetMapping("/getAllByNameAndAddress/{name}/{address}")
    public List<Person> getAllByNameAndAddress(@PathVariable String name, @PathVariable String address) {
        return personService.getAllByNameAndAddress(name, address);
    }

    @GetMapping("/getAllByNameAndCityWithOrder/{name}/{city}")
    public List<Person> getAllByNameAndCityWithOrder(@PathVariable String name, @PathVariable String city) {
        return personService.getAllByNameAndCityWithOrder(name, city);
    }

    @GetMapping("/getAllCount")
    public int getAllCount() {
        return personService.getAllCount();
    }

    @GetMapping("/getAllUsingPagination")
    public List<Person> getAllPerson() {
        return personService.getAllUsingPagination();
    }
}
