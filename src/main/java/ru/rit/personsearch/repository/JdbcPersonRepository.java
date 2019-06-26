package ru.rit.personsearch.repository;

import ru.rit.personsearch.model.Person;

import java.util.List;

public class JdbcPersonRepository implements PersonRepository {

    @Override
    public List<Person> get(String firstName, String lastName, String patronymic, Integer cityId) {
        return null;
    }

    @Override
    public List<Person> getByCar(String model, String license) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }
}
