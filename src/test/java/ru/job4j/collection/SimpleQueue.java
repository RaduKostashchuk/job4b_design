package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        T result;
        int index = 0;
        while (index != size) {
            out.push(in.pop());
            index++;
        }
        result = out.pop();
        size--;
        index = 0;
        while (index != size) {
            in.push(out.pop());
            index++;
        }
        return result;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
