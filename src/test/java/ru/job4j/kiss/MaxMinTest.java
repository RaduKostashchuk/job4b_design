package ru.job4j.kiss;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MaxMinTest {

    @Test
    public void whenEmptyThenNull() {
        var maxMin = new MaxMin();
        List<Integer> input = List.of();
        assertThat(maxMin.max(input, Comparator.naturalOrder()), IsNull.nullValue());
        assertThat(maxMin.min(input, Comparator.naturalOrder()), IsNull.nullValue());
    }

    @Test
    public void whenOneElementThenReturnThatOneElement() {
        var maxMin = new MaxMin();
        List<Integer> input = List.of(23);
        assertThat(maxMin.max(input, Comparator.naturalOrder()), is(23));
        assertThat(maxMin.min(input, Comparator.naturalOrder()), is(23));
    }

    @Test
    public void whenMultipleEntriesThenMax() {
        var maxMin = new MaxMin();
        List<Integer> input = List.of(12, 1, 98, 33);
        assertThat(maxMin.max(input, Comparator.naturalOrder()), is(98));
        assertThat(maxMin.min(input, Comparator.naturalOrder()), is(1));
    }

}