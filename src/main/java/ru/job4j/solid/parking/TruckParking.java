package ru.job4j.solid.parking;

public class TruckParking implements Subparking {
    private final int size;
    private final Vehicle[] places;

    public TruckParking(int size) {
        this.size = size;
        places = new Vehicle[size];
    }

    @Override
    public int accept(Vehicle vehicle) {
        int result = -1;
        if (vehicle.getSize() != 1) {
            for (int i = 0; i < size; i++) {
                if (places[i] == null) {
                    result = i;
                    break;
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
        Vehicle truck;
        for (int i = 0; i < size; i++) {
            truck = places[i];
            if (truck != null) {
                if (truck.getNumber().equals(vehicle.getNumber())) {
                    places[i] = null;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
