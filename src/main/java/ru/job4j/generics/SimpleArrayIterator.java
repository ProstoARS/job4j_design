package ru.job4j.generics;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<E> implements Iterator<E> {
    private final E[] data;
    private int index = 0;
    private int pointer = 0;

    public SimpleArrayIterator(E[] data, int pointer) {
        this.data = data;
        this.pointer = pointer;
    }

    public boolean hasNext() {
        return index < pointer;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
