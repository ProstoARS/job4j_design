package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private int index = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T temp = null;
        for (int i = 0; i < index * 2 - 1; i++) {
            if (i < index) {
                out.push(in.pop());
            } else {
                in.push(out.pop());
            }
            if (i == index - 1) {
                temp = out.pop();
            }
        }
        index--;
        return temp;
    }

    public void push(T value) {
        in.push(value);
        index++;
    }
}