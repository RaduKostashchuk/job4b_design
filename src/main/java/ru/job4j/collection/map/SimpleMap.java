package ru.job4j.collection.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result;
        int hashCode = (key == null) ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] != null) {
            result = false;
        } else {
            table[indexFor(hash(hashCode))] = new MapEntry<>(key, value);
            count++;
            modCount++;
            if (count >= capacity * LOAD_FACTOR) {
                expand();
            }
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int indexFor(K key) {
        int hashCode = (key == null) ? 0 : key.hashCode();
        return hash(hashCode) & (capacity - 1);
    }

    private void expand() {
        int capacityOld = capacity;
        capacity = capacity << 1;
        MapEntry<K, V>[] tableNew = new MapEntry[capacity];
        for (int index = 0; index < capacityOld; index++) {
            if (table[index] != null) {
                tableNew[indexFor(table[index].key)] = table[index];
            }
        }
        table = tableNew;
    }

    @Override
    public V get(K key) {
        int hashCode = (key == null) ? 0 : key.hashCode();
        V result;
        if (count == 0) {
            result = null;
        } else {
            int index = indexFor(hash(hashCode));
            result = table[index] == null
                ? null : table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int hashCode = (key == null) ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] != null) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int position = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (count != 0) {
                    for (int index = position; index < table.length; index++) {
                        if (table[index] != null) {
                            position = index;
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[position++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
