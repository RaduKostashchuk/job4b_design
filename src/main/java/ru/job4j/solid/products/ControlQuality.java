package ru.job4j.solid.products;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    public void distribute(Food product, List<Store> stores) {
        for (Store store : stores) {
            if (store.add(product)) {
                break;
            }
        }
    }

    public void resort(List<Store> stores) {
        List<Food> temp = new ArrayList<>();
        for (Store store : stores) {
            temp.addAll(store.findAll());
            store.clear();
        }

        for (Food el : temp) {
            distribute(el, stores);
        }
    }
}
