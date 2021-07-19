package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private int indexIn = 0;
    private int indexOut = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (indexOut == 0) {
            while (indexIn != 0) {
               out.push(in.pop());
               indexIn--;
               indexOut++;
            }
        }
        indexOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        indexIn++;
    }
}