package ru.rit.personsearch;

import ru.rit.personsearch.repository.PersonRepository;

public class MainSearch {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        PersonRepository personRepository = Config.get().getRepository();



    }
}
