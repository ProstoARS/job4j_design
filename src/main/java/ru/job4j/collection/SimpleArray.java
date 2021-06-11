package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] data;

    private int pointer = 0;

    SimpleArray() {
        data = (T[]) new Object[1];
    }

    public T get(int index) {
        int in = Objects.checkIndex(index, pointer);
        return data[in];
    }

    public void add(T model) {
        if (pointer == data.length) {
            T[] temp = data;
            data = (T[]) new Object[temp.length + 1];
            System.arraycopy(temp, 0, data, 0, temp.length);
        }
        data[pointer] = model;
        pointer++;
    }

    @Override
    public Iterator<T> iterator() {
       return new SimpleIterator<>(data, pointer);
    }

    private class SimpleIterator<E> implements Iterator<E> {
        int index;
        E[] data;
        private int modCount = 0;

        public SimpleIterator(E[] data, int pointer) {
            this.data = data;
            this.modCount = pointer;
        }

        @Override
        public boolean hasNext() {
            if (pointer != modCount) {
                throw new ConcurrentModificationException();
            }
            return index < modCount;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[index++];
        }
    }
}
