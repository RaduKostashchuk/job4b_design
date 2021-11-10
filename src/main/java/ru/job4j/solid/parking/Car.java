package ru.job4j.solid.parking;

public class Car implements Vehicle {
    private int size;
    private String number;

    public Car(int size, String number) {
        this.size = size;
        this.number = number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getNumber() {
        return number;
    }
}
