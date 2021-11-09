package ru.job4j.solid.products;

import java.util.List;

public class ControlQuality {

    public void distribute(Food product, List<Store> stores) {
        for (Store store : stores) {
            if (store.add(product)) {
                break;
            }
        }
    }
}
