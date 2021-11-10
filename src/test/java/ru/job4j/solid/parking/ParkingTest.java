package ru.job4j.solid.parking;


import org.junit.Ignore;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class ParkingTest {

    @Ignore
    @Test
    public void whenAddCarToEmptyCarParkingThenTrue() {
        Subparking carParking = new CarParking(new Vehicle[10]);
        Parking parking = new Parking(List.of(carParking));
        Vehicle car = new Car(1, "040");
        assertTrue(parking.distribute(car));
    }

    @Ignore
    @Test
    public void whenAddTruckToEmptyTruckParkingThenTrue() {
        Subparking truckParking = new TruckParking(new Vehicle[10]);
        Parking parking = new Parking(List.of(truckParking));
        Vehicle truck = new Truck(4, "111");
        assertTrue(parking.distribute(truck));
    }

    @Ignore
    @Test
    public void whenAddCarToEmptyTruckParkingThenFalse() {
        Subparking truckParking = new TruckParking(new Vehicle[10]);
        Parking parking = new Parking(List.of(truckParking));
        Vehicle car = new Car(1, "040");
        assertFalse(parking.distribute(car));
    }

    @Ignore
    @Test
    public void whenAddTruckToEmptyCarParkingThenTrue() {
        Subparking carParking = new CarParking(new Vehicle[10]);
        Parking parking = new Parking(List.of(carParking));
        Vehicle truck = new Truck(3, "020");
        assertTrue(parking.distribute(truck));
    }

    @Ignore
    @Test
    public void whenAddCarToFullCarParkingThenFalse() {
        Subparking carParking = new CarParking(new Vehicle[1]);
        Parking parking = new Parking(List.of(carParking));
        Vehicle car1 = new Car(1, "020");
        Vehicle car2 = new Car(1, "777");
        parking.distribute(car1);
        assertFalse(parking.distribute(car2));
    }

    @Ignore
    @Test
    public void whenAddTruckToFullTruckParkingThenFalse() {
        Subparking truckParking = new TruckParking(new Vehicle[1]);
        Parking parking = new Parking(List.of(truckParking));
        Vehicle truck1 = new Truck(2, "020");
        Vehicle truck2 = new Truck(2, "777");
        parking.distribute(truck1);
        assertFalse(parking.distribute(truck2));
    }

    @Ignore
    @Test
    public void whenAddTruckToCarParkingWithNotEnoughSpaceThenFalse() {
        Subparking carParking = new CarParking(new Vehicle[4]);
        Parking parking = new Parking(List.of(carParking));
        Vehicle car1 = new Car(1, "020");
        Vehicle car2 = new Car(1, "777");
        Vehicle car3 = new Car(1, "098");
        Vehicle truck = new Truck(2, "050");
        parking.distribute(car1);
        parking.distribute(car2);
        parking.distribute(car3);
        parking.withdraw(car2);
        assertFalse(parking.distribute(truck));
    }

    @Ignore
    @Test
    public void whenAddTruckToCarParkingWithEnoughSpaceThenTrue() {
        Subparking carParking = new CarParking(new Vehicle[4]);
        Parking parking = new Parking(List.of(carParking));
        Vehicle car1 = new Car(1, "020");
        Vehicle car2 = new Car(1, "777");
        Vehicle truck = new Truck(2, "020");
        parking.distribute(car1);
        parking.distribute(car2);
        assertTrue(parking.distribute(truck));
    }

    @Ignore
    @Test
    public void whenAddCarsAndTrucksToEmptyParkingsThenTrue() {
        Subparking carParking = new CarParking(new Vehicle[2]);
        Subparking truckParking = new TruckParking(new Vehicle[2]);
        Parking parking = new Parking(List.of(carParking, truckParking));
        Vehicle car1 = new Car(1, "020");
        Vehicle car2 = new Car(1, "777");
        Vehicle truck1 = new Truck(2, "020");
        Vehicle truck2 = new Truck(2, "737");
        assertTrue(parking.distribute(car1));
        assertTrue(parking.distribute(car2));
        assertTrue(parking.distribute(truck1));
        assertTrue(parking.distribute(truck2));
    }
}