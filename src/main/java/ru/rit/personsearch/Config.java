package ru.rit.personsearch;

import ru.rit.personsearch.repository.JdbcPersonRepository;
import ru.rit.personsearch.repository.PersonRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected static final String PROPS = "/personsearch.properties";
//    protected static final File PROPS = new File("config\\personsearch.properties");
    private static final Config INSTANCE = new Config();

    private final PersonRepository personRepository;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try(InputStream is = Config.class.getResourceAsStream(PROPS)){
//        try(InputStream is = new FileInputStream(PROPS)){
            Properties props = new Properties();
            props.load(is);
            personRepository = new JdbcPersonRepository(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS);
        }
    }

    public PersonRepository getRepository() {
        return personRepository;
    }

}