package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return test(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return test(value, comparator.reversed());
    }

    private <T> T test(List<T> value, Comparator<T> comparator) {
        T result = null;
        T current;
        int size = value.size();
        if (size == 1) {
            result = value.get(0);
        } else if (size > 1) {
            result = value.get(0);
            for (int i = 1; i < size; i++) {
                current = value.get(i);
                if (comparator.compare(result, current) < 0) {
                    result = current;
                }
            }
        }
        return result;
    }
}
