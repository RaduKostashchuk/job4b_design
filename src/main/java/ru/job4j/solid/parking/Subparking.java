package ru.job4j.solid.parking;

public interface Subparking {

    boolean accept(Vehicle vehicle);

    boolean add(Vehicle vehicle);

    boolean withdraw(Vehicle vehicle);
}
