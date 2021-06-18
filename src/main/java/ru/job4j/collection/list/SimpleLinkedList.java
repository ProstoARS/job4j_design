package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    transient Node<E> fstNode;
    private final Node<E> lstNode;
    private int size = 0;
    private int modCount = 0;

    public SimpleLinkedList() {
        this.lstNode = new Node<>(null, null, null);
        this.fstNode = new Node<>(null, null, lstNode);
    }

    @Override
    public void add(E value) {
        Node<E> addedNode = new Node<>(lstNode.getPrevElement(), value, lstNode);
        if (size == 0) {
            fstNode.setNextElement(addedNode);
        } else {
            lstNode.getPrevElement().setNextElement(addedNode);
        }
        lstNode.setPrevElement(addedNode);
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        int in = Objects.checkIndex(index, size);
        Node<E> temp = fstNode;
        for (int i = 0; i <= in; i++) {
            if (i == in) {
                return temp.getNextElement().getCurrentElement();
            }
            temp.setNextElement(temp.getNextElement().getNextElement());
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator<>(modCount);
    }

    private static class Node<E> {
        private final E currentElement;
        private Node<E> nextElement;
        private Node<E> prevElement;

        public Node(Node<E> prevElement, E currentElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }

        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public void setPrevElement(Node<E> prevElement) {
            this.prevElement = prevElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }

        public Node<E> getPrevElement() {
            return prevElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }
    }

    private class LinkedIterator<T> implements Iterator<T> {
        Node<E> temp = fstNode;
        private final int expectedModCount;

        public LinkedIterator(int expectedModCount) {
            this.expectedModCount = expectedModCount;
        }

        @Override
        public boolean hasNext() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            } else {
                if (temp.getNextElement() == lstNode) {
                    return false;
                }
                return true;
            }
        }

        @Override
        public T next() {
            temp = temp.getNextElement();
            return (T) temp.getCurrentElement();
        }
    }

}