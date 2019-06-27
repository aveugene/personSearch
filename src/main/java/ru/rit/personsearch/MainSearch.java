package ru.rit.personsearch;

import ru.rit.personsearch.repository.PersonRepository;
import ru.rit.personsearch.to.PersonTo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainSearch {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        PersonRepository personRepository = Config.get().getRepository();

        Map<String, String> requestParams = new TreeMap<>();
        requestParams.put("first_name", "Клемент");
        requestParams.put("last_name", "Бобылёв");
        requestParams.put("patronymic", "Двумашинович");
        List<PersonTo> newList = personRepository.get(requestParams);
        newList.forEach(System.out::println);

        newList = personRepository.getAll();
        newList.forEach(System.out::println);
    }
}
