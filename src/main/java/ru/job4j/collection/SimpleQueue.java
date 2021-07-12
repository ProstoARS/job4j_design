package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private int index = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
            for (int i = 0; i < index; i++) {
                out.push(in.pop());
            }
            T temp = out.pop();
            index--;
        for (int i = 0; i < index; i++) {
            in.push(out.pop());
        }
        return temp;
    }

    public void push(T value) {
        in.push(value);
        index++;
    }
}