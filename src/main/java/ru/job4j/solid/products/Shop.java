package ru.job4j.solid.products;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private final List<Food> products = new ArrayList<>();

    @Override
    public boolean accept(Food product) {
        double expPercent = getExpirationPercent(product);
        return !(expPercent >= 100 || expPercent < 25);
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public boolean add(Food product) {
        boolean result = false;
        if (accept(product)) {
            double expPercent = getExpirationPercent(product);
            if (expPercent > 75) {
                product.setDiscount(10);
            }
            products.add(product);
            result = true;
        }
        return result;
    }
}
