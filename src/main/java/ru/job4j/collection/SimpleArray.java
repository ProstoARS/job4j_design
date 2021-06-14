package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private T[] data;

    private int pointer = 0;

    private int modCount = 0;

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
            data = Arrays.copyOf(temp, temp.length * 2);
        }
        data[pointer] = model;
        pointer++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
       return new SimpleIterator<>(data, pointer);
    }

    private class SimpleIterator<E> implements Iterator<E> {
        int index;
        E[] data;
        private int expectedModCount = 0;

        public SimpleIterator(E[] data, int pointer) {
            this.data = data;
            this.expectedModCount = pointer;
        }

        @Override
        public boolean hasNext() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
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
}
