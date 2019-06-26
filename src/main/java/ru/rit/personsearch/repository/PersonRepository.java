package ru.rit.personsearch.repository;

import ru.rit.personsearch.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> get(String firstName, String lastName, String patronymic, Integer cityId);

    List<Person> getByCar(String model, String license);

    List<Person> getAll();
}
