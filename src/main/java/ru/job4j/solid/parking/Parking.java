package ru.job4j.solid.parking;

import java.util.List;

public class Parking {
    private final List<Subparking> parkings;

    public Parking(List<Subparking> subparkings) {
        this.parkings = subparkings;
    }

    public boolean distribute(Vehicle vehicle) {
        boolean result = false;
        for (Subparking el : parkings) {
            if (el.accept(vehicle)) {
                el.add(vehicle);
                result = true;
                break;
            }
        }
        return result;
    }

    boolean withdraw(Vehicle vehicle) {
        return true;
    }
}
