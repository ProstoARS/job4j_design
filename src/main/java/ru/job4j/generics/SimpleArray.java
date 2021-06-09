package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int pointer = 0;

    public SimpleArray(int size) {
        array = (T[]) new Object[size];
    }

    public void add(T model) {
        array[pointer++] = model;
    }

    public void set(int index, T model) {
        int in = Objects.checkIndex(index, array.length);
        array[in] = model;
    }

    public void remove(int index) {
        int in = Objects.checkIndex(index, array.length);
        System.arraycopy(array, index + 1, array, index, pointer);
        this.pointer--;
    }

    public T get(int index) {
        int in = Objects.checkIndex(index, array.length);
        return array[in];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>(array);
    }
}
