package ru.rit.personsearch.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Person {
    private int id;
    private String firstName;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public City getCity() {
        return city;
    }

    public Set<Car> getCars() {
        return cars;
    }

    private String lastName;
    private String patronymic;
    private City city;
    private Set<Car> cars;

    public Person(int id, String firstName, String lastName, String patronymic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.cars = new HashSet<>();
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void addCar (Car car) {
        this.cars.add(car);
    }

    @Override
    public String toString() {
        return "{\"" + id + "\":{"
                + "    \"first_name\":\"" + firstName + "\""
                + ",   \"last_name\":\"" + lastName + "\""
                + ",   \"patronymic\":\"" + patronymic + "\""
                + ",   \"city_name\":" + city
                + ",   \"cars\":" + cars
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                patronymic.equals(person.patronymic) &&
                city.equals(person.city) &&
                cars.equals(person.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, city, cars);
    }
}