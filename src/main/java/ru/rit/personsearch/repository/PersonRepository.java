package ru.rit.personsearch.repository;

import ru.rit.personsearch.model.Person;
import ru.rit.personsearch.to.PersonTo;

import java.util.List;
import java.util.Map;

public interface PersonRepository {
    List<PersonTo> get(Map<String, String> requestParams);

    List<PersonTo> getAll();
}
