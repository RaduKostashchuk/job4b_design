package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        T result = null;
        for (int index = 0; index < 2 * size + 1; index++) {
            if (index < size) {
                out.push(in.pop());
                continue;
            }
            if (index == size) {
                result = out.pop();
                index++;
                continue;
            }
            in.push(out.pop());
        }
        size--;
        return result;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
