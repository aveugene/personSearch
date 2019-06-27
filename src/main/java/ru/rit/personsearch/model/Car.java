package ru.rit.personsearch.model;

import java.util.Objects;

public class Car {
    private String model;
    private String license;

    public Car(String model, String license) {
        this.model = model;
        this.license = license;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", license='" + license + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return model.equals(car.model) &&
                license.equals(car.license);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, license);
    }
}
