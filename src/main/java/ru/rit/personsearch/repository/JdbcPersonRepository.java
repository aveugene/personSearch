package ru.rit.personsearch.repository;

import ru.rit.personsearch.model.Person;
import ru.rit.personsearch.util.SqlHelper;

import java.sql.DriverManager;
import java.util.List;

public class JdbcPersonRepository implements PersonRepository {
    private final SqlHelper sqlHelper;

    public JdbcPersonRepository(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

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
