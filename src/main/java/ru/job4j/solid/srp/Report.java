package ru.job4j.solid.srp;

import java.util.function.Predicate;

public interface Report {

    String generate(Predicate<Employee> filter);
}
