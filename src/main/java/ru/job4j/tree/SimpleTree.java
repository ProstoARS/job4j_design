package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> nodeParentOpt = findBy(parent);
        Optional<Node<E>> nodeChildOpt = findBy(child);
        if (nodeChildOpt.isPresent()) {
            return rsl;
        }
        Node<E> c = new Node<>(child);
        nodeParentOpt.ifPresent(pNode -> pNode.children.add(c));
        rsl = true;
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(v -> v.value.equals(value));
    }

    @Override
    public boolean isBinary() {
        Optional<Node<E>> rsl = findByPredicate(v -> v.children.size() > 2);
        return rsl.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

}
