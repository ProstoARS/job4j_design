package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = 0; i < data.length; i++) {
            if (data[column].length > 0) {
                return true;
            } else {
                column++;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (row >= data[column].length) {
            row = 0;
            column++;
            this.hasNext();
        }
        return data[column][row++];
    }

}