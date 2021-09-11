package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArrayList;


import java.util.Iterator;
import java.util.Optional;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        for (T element : set) {
            if (Optional.ofNullable(element).equals(Optional.ofNullable(value))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
