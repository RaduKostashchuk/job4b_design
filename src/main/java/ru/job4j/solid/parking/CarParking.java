package ru.job4j.solid.parking;

public class CarParking implements Subparking {
    private Vehicle[] places;

    public CarParking(Vehicle[] places) {
        this.places = places;
    }

    @Override
    public boolean accept(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean withdraw(Vehicle vehicle) {
        return false;
    }
}
