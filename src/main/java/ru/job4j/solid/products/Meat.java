package ru.job4j.solid.products;

import java.util.Calendar;

public class Meat extends Food {
    public Meat(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
