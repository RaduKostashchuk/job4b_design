package ru.job4j.collection.map;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleMapTest {

    SimpleMap<Integer, String> map;

    @Before
    public void init() {
        map = new SimpleMap<>();
    }

    @Test
    public void whenPut2ThenGet2() {
        map.put(12, "test");
        map.put(34, "test 2");
        assertThat(map.get(12), is("test"));
        assertThat(map.get(34), is("test 2"));
    }

    @Test
    public void whenEmptyMap() {
        assertThat(map.get(null), IsNull.nullValue());
        assertThat(map.get(12), IsNull.nullValue());
    }

    @Test
    public void whenPutExisting() {
        map.put(12, "test");
        assertFalse(map.put(12, "data"));
    }

    @Test
    public void whenPutNull() {
        map.put(null, "test");
        assertThat(map.get(null), is("test"));
        assertFalse(map.put(null, "data"));
    }

    @Test
    public void whenDeleteExisting() {
        map.put(11, "test");
        map.put(12, "test 2");
        assertTrue(map.remove(11));
        assertTrue(map.remove(12));
    }

    @Test
    public void whenAttemptDeleteNotExisting() {
        assertFalse(map.remove(11));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void wnehTestFailFast() {
        map.put(11, "value 1");
        Iterator<Integer> it = map.iterator();
        map.put(22, "value 2");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorOnEmptyMap() {
        Iterator<Integer> it = map.iterator();
        it.next();
    }

    @Test
    public void testExpand() {
        map.put(11, "a");
        map.put(22, "b");
        map.put(33, "c");
        map.put(44, "d");
        map.put(55, "e");
        map.put(66, "f");
        map.put(77, "g");
        map.put(88, "h");
        map.put(99, "i");
        Iterator<Integer> it = map.iterator();
        List<Integer> result = new ArrayList<>();
        result.add(it.next());
        result.add(it.next());
        result.add(it.next());
        result.add(it.next());
        result.add(it.next());
        result.add(it.next());
        result.add(it.next());
        result.add(it.next());
        result.add(it.next());
        result.sort(Comparator.naturalOrder());
        assertThat(result, is(Arrays.asList(11, 22, 33, 44, 55, 66, 77, 88, 99)));
    }

}