package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        com.company.PeopleStats peopleStats = new com.company.PeopleStats(Paths.get("śćieżka", "do", "pliku"));

        System.out.println(String.format("Liczba osób: %d", peopleStats.count()));
        System.out.println(String.format("Liczba osób z unikalnymi nazwiskami: %d", peopleStats.countUniqueLastNames()));
    }
}

class PeopleStats {
    private final List<com.company.Person> people;

    public PeopleStats(Path inputFilePath) {
        try {
            people = Files.lines(inputFilePath)
                    .map(line -> line.split("\t"))
                    .map(chunks -> new com.company.Person(chunks[0], chunks[1]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public long count() {
        return people.size();
    }

    public long countUniqueLastNames() {
        return people.stream()
                .map(com.company.Person::getLastName)
                .distinct()
                .count();
    }
}

class Person {
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}

/// ala ma kota a kot ma ale
