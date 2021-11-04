package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return test(value, x -> x < 0, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return test(value, x -> x > 0, comparator);
    }

    private <T> T test(List<T> value, Predicate<Integer> predicate, Comparator<T> comparator) {
        int size = value.size();
        T result = size == 0 ? null : value.get(0);
        T current;
        if (size > 1) {
            result = value.get(0);
            for (int i = 1; i < size; i++) {
                current = value.get(i);
                if (predicate.test(comparator.compare(result, current))) {
                    result = current;
                }
            }
        }
        return result;
    }
}
