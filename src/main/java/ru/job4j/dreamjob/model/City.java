package ru.job4j.dreamjob.model;

import java.util.Objects;

public class City {

    private final int id;

    private final String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        City city = (City) object;
        return id == city.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
