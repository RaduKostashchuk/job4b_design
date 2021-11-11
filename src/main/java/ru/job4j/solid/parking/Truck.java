package ru.job4j.solid.parking;

public class Truck implements Vehicle {
    private final int size;
    private final String number;

    public Truck(int size, String number) {
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
