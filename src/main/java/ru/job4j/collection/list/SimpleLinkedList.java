package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        if (size != 0) {
            last.next = new Node<>(value, last, null);
            last = last.next;
        } else {
            first = new Node<>(value, null, null);
            last = first;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = first;
        while (index != 0) {
            result = result.next;
            index--;
        }
        return result.content;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            Node<E> position = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return position != null;
            }
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = position.content;
                position = position.next;
                return result;
            }
        };
    }

    private static class Node<E> {
        E content;
        Node<E> next;
        Node<E> prev;

        public Node(E content, Node<E> prev, Node<E> next) {
            this.content = content;
            this.prev = prev;
            this.next = next;
        }
    }
}
