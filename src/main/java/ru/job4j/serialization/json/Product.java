package ru.job4j.serialization.json;

public class Product {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + '}';
    }
}
