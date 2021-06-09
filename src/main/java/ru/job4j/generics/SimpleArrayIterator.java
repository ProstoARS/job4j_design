package ru.job4j.generics;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<E> implements Iterator<E> {
    private final E[] data;
    private int index = 0;

    public SimpleArrayIterator(E[] data) {
        this.data = data;
    }

    public boolean hasNext() {
        return index < data.length;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
