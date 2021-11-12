package ru.job4j.solid.dip;

import java.util.HashMap;
import java.util.Map;

public class Parking {
    private final Map<String, Car> parking = new HashMap<>();
    private final int size;
    private int count = 0;

    public Parking(int size) {
        this.size = size;
    }

    public boolean add(Car car) {
        boolean result = false;
        String number = car.getNumber();
        if (!parking.containsKey(number) && count < size) {
            parking.put(number, car);
            count++;
            result = true;
        }
        return result;
    }

    public boolean remove(Car car) {
        boolean result = false;
        String number = car.getNumber();
        if (parking.containsKey(number)) {
            parking.remove(number);
            count--;
            result = true;
        }
        return result;
    }

    public void getUsage() {
        System.out.println("Places used: " + count);
        System.out.println("Places free: " + (size - count));
    }
}
