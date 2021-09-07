package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    public final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data[row].length == 0) {
            row++;
            if (row > data.length - 1) {
                return false;
            }
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        int result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        result = data[row][column];
        if (column < data[row].length - 1) {
            column++;
        } else {
            row++;
            column = 0;
        }
        return result;
    }
}
