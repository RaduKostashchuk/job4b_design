package ru.job4j.solid.products;

import java.util.Iterator;
import java.util.List;

public interface Store {

    List<Food> findAll();
    void add(Food product);
    Iterator<Food> iterator();
}
