package ru.rit.personsearch.repository;

import ru.rit.personsearch.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonRepository {
    List<Person> get(Map<String, String> requestParams);

    List<Person> getAll();
}
