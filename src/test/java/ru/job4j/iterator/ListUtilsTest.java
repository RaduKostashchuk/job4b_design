package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddBeforeFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.addBefore(input, 0, 1);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, Is.is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> filter = x -> x == 3;
        ListUtils.removeIf(input, filter);
        assertThat(input, Is.is(Arrays.asList(1, 2)));
    }

    @Test
    public void whenRemoveIfFromEmpty() {
        List<Integer> input = new ArrayList<>();
        Predicate<Integer> filter = x -> x == 3;
        ListUtils.removeIf(input, filter);
        assertThat(input, Is.is(Arrays.asList()));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> filter = x -> x == 3;
        ListUtils.replaceIf(input, filter, 10);
        System.out.println(input);
        assertThat(input, Is.is(Arrays.asList(1, 2, 10)));
    }

    @Test
    public void whenReplaceIfInEmpty() {
        List<Integer> input = new ArrayList<>();
        Predicate<Integer> filter = x -> x == 3;
        ListUtils.replaceIf(input, filter, 10);
        System.out.println(input);
        assertThat(input, Is.is(Arrays.asList()));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 4, 5));
        ListUtils.removeAll(input, list);
        assertThat(input, Is.is(Arrays.asList(2, 3)));
    }

    @Test
    public void whenRemoveFromEmpty() {
        List<Integer> input = new ArrayList<>();
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 4, 5));
        ListUtils.removeAll(input, list);
        assertThat(input, Is.is(Arrays.asList()));
    }

    @Test
    public void whenRemoveListIsEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> list = new ArrayList<>();
        ListUtils.removeAll(input, list);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }
}