package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        for (String str : mem.keySet()) {
            if (str.contains(id)) {
                mem.replace(id, model);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (String str : mem.keySet()) {
            if (str.contains(id)) {
                mem.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (String str : mem.keySet()) {
            if (str.contains(id)) {
               return mem.get(id);
            }
        }
        return null;
    }
}

