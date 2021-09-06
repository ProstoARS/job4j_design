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
        int h = Objects.hash(key);
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
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private int indexFor(int hash) {
        return hash & (tableSize() - 1);
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
        int hash = Objects.hash(key);
        int index = indexFor(hash(hash));
        if (table[index] != null) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int hash = Objects.hash(key);
        int index = indexFor(hash(hash));
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int point = 0;
            final int expectedModCount = count;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                for (MapEntry<K, V> kvMapEntry : table) {
                    if (kvMapEntry != null) {
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
    }
}

