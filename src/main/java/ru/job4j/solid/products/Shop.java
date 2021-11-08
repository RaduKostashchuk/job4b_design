package ru.job4j.solid.products;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shop implements Store {
    private final List<Food> products = new ArrayList<>();

    @Override
    public List<Food> findAll() {
        return products;
    }

    @Override
    public void add(Food product) {
        products.add(product);
    }

    @Override
    public Iterator<Food> iterator() {
        return products.iterator();
    }
}
