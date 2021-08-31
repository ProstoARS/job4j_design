package ru.job4j.collection.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private final int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        MapEntry<K, V> map = new MapEntry<>(key, value);
        int h = map.hashCode();
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null && kvMapEntry.hashCode() == h) {
                if (kvMapEntry.equals(map)) {
                    return false;
                }
            }
        }
        int index = indexFor(hash(h));
        if (table[index] == null) {
            table[index] = map;
            count++;
            modCount++;
            expand();
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        int h = 37;
        return Math.abs(h * 17 + hashCode);
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private void expand() {
        if (count > table.length * LOAD_FACTOR) {
            MapEntry<K, V>[] temp = table;
            table = Arrays.copyOf(temp, temp.length * 2);
        }
    }

    public int size() {
        return count;
    }

    public int tableSize() {
        return table.length;
    }

    @Override
    public V get(K key) {
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null && kvMapEntry.key.equals(key)) {
                return kvMapEntry.value;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].key.equals(key)) {
                table[i] = null;
                count--;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int point = 0;
            final int expectedModCount = count;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = 0; i < table.length; i++) {
                    if (table[i] != null) {
                        return true;
                    }
                    point++;
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

}

