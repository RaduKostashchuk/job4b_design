package ru.job4j.solid.parking;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenAddCarToEmptyCarParkingThenTrue() {
        Subparking carParking = new CarParking(10);
        Parking parking = new Parking(List.of(carParking));
        Vehicle car = new Car("040");
        assertTrue(parking.distribute(car));
    }

    @Test
    public void whenAddTruckToEmptyTruckParkingThenTrue() {
        Subparking truckParking = new TruckParking(10);
        Parking parking = new Parking(List.of(truckParking));
        Vehicle truck = new Truck(4, "111");
        assertTrue(parking.distribute(truck));
    }

    @Test
    public void whenAddCarToEmptyTruckParkingThenFalse() {
        Subparking truckParking = new TruckParking(10);
        Parking parking = new Parking(List.of(truckParking));
        Vehicle car = new Car("040");
        assertFalse(parking.distribute(car));
    }

    @Test
    public void whenAddTruckToEmptyCarParkingThenTrue() {
        Subparking carParking = new CarParking(10);
        Parking parking = new Parking(List.of(carParking));
        Vehicle truck = new Truck(3, "020");
        assertTrue(parking.distribute(truck));
    }

    @Test
    public void whenAddTruckToSmallCarParkingThenFalse() {
        Subparking carParking = new CarParking(3);
        Parking parking = new Parking(List.of(carParking));
        Vehicle truck = new Truck(4, "020");
        assertFalse(parking.distribute(truck));
    }

    @Test
    public void whenRemoveTruckFromCarParkingThenAllPlacesMustBeEmpty() {
        Subparking carParking = new CarParking(4);
        Parking parking = new Parking(List.of(carParking));
        Vehicle truck = new Truck(3, "020");
        Vehicle car1 = new Car("020");
        Vehicle car2 = new Car("777");
        Vehicle car3 = new Car("098");
        Vehicle car4 = new Car("099");
        parking.distribute(car1);
        parking.distribute(truck);
        parking.withdraw(truck);
        assertTrue(parking.distribute(car2));
        assertTrue(parking.distribute(car3));
        assertTrue(parking.distribute(car4));
    }


    @Test
    public void whenAddCarToFullCarParkingThenFalse() {
        Subparking carParking = new CarParking(1);
        Parking parking = new Parking(List.of(carParking));
        Vehicle car1 = new Car("020");
        Vehicle car2 = new Car("777");
        parking.distribute(car1);
        assertFalse(parking.distribute(car2));
    }

    @Test
    public void whenAddTruckToFullTruckParkingThenFalse() {
        Subparking truckParking = new TruckParking(1);
        Parking parking = new Parking(List.of(truckParking));
        Vehicle truck1 = new Truck(2, "020");
        Vehicle truck2 = new Truck(2, "777");
        parking.distribute(truck1);
        assertFalse(parking.distribute(truck2));
    }

    @Test
    public void whenAddTruckToCarParkingWithNotEnoughSpaceThenFalse() {
        Subparking carParking = new CarParking(4);
        Parking parking = new Parking(List.of(carParking));
        Vehicle car1 = new Car("020");
        Vehicle car2 = new Car("777");
        Vehicle car3 = new Car("098");
        Vehicle truck = new Truck(2, "050");
        parking.distribute(car1);
        parking.distribute(car2);
        parking.distribute(car3);
        parking.withdraw(car2);
        assertFalse(parking.distribute(truck));
    }

        @Test
    public void whenAddTruckToCarParkingWithEnoughSpaceThenTrue() {
        Subparking carParking = new CarParking(4);
        Parking parking = new Parking(List.of(carParking));
        Vehicle car1 = new Car("020");
        Vehicle car2 = new Car("777");
        Vehicle truck = new Truck(2, "020");
        parking.distribute(car1);
        parking.distribute(car2);
        assertTrue(parking.distribute(truck));
    }

    @Test
    public void whenAddCarsAndTrucksToEmptyParkingsThenTrue() {
        Subparking carParking = new CarParking(2);
        Subparking truckParking = new TruckParking(2);
        Parking parking = new Parking(List.of(carParking, truckParking));
        Vehicle car1 = new Car("020");
        Vehicle car2 = new Car("777");
        Vehicle truck1 = new Truck(2, "020");
        Vehicle truck2 = new Truck(2, "737");
        assertTrue(parking.distribute(car1));
        assertTrue(parking.distribute(car2));
        assertTrue(parking.distribute(truck1));
        assertTrue(parking.distribute(truck2));
    }
}