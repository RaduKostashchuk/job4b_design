package ru.job4j.solid.parking;

public class Car implements Vehicle {
    public static final int SIZE = 1;
    private final String number;

    public Car(String number) {
        this.number = number;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String getNumber() {
        return number;
    }
}
