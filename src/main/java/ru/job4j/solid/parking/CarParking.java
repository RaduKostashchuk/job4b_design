package ru.job4j.solid.parking;


public class CarParking implements Subparking {
    private final int size;
    private final Vehicle[] places;

    public CarParking(int size) {
        this.size = size;
        places = new Vehicle[size];
    }

    @Override
    public int accept(Vehicle vehicle) {
        int result = -1;
        int vehicleSize = vehicle.getSize();
        int countPlaces = 0;
        if (vehicleSize <= size) {
            for (int i = 0; i < size; i++) {
                if (places[i] == null) {
                    countPlaces++;
                    if (countPlaces == vehicleSize) {
                        result = i - vehicleSize + 1;
                        break;
                    }
                } else {
                    countPlaces = 0;
                }
            }
        }
        return result;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        int placeId = accept(vehicle);
        if (placeId > -1) {
            places[placeId] = vehicle;
        }
        return placeId > -1;
    }

    @Override
    public boolean withdraw(Vehicle vehicle) {
        boolean result = false;
        Vehicle car;
        for (int i = 0; i < size; i++) {
            car = places[i];
            if (car != null) {
                if (car.getNumber().equals(vehicle.getNumber())) {
                    for (int j = 0; j < vehicle.getSize(); j++) {
                        places[i] = null;
                    }
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
