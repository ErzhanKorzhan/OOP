package org.example;

import java.util.*;

public class HashTable<K, V> implements Iterable<Entry<K, V>> {
    private LinkedList<Entry<K, V>>[] table;
    private int size;          // Количество элементов в таблице
    private int modCount;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new LinkedList[16];
        modCount = 0;
        size = 0;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new HashTableIterator();
    }

    private int getIndex(K key) {
        return (key == null ? 0 : Math.abs(key.hashCode())) % table.length;
    }

    public void put(K key, V value) {
        if (size >= table.length * 0.75f) {
            resize();
        }

        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        modCount++;
        size++;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        @SuppressWarnings("unchecked")
        HashTable<K, V> other = (HashTable<K, V>) obj;
        if (this.size != other.size) return false;
        for (int i = 0; i < this.size; i++) {
            LinkedList<Entry<K, V>> list = table[i];
            LinkedList<Entry<K, V>> otherList = other.table[i];
            if (!Objects.equals(list, otherList)) return false;
        }
        return true;
    }

    public void update(K key, V value) {
        int index = getIndex(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    table[index].remove(entry);
                    size--;
                    modCount++;
                    return;
                }
            }
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Entry<K, V> entry : this) {
            sb.append(entry).append(", ");
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append("}");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        size = 0;
        for (LinkedList<Entry<K, V>> list : oldTable) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private class HashTableIterator implements Iterator<Entry<K, V>> {
        private final int expectedModCount = modCount;
        private int currentIndex = 0;
        private Iterator<Entry<K, V>> listIterator;

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("The hash table has been modified");
            }
            while (listIterator == null || !listIterator.hasNext()) {
                if (currentIndex >= table.length) {
                    return false;
                }
                if (table[currentIndex] != null) {
                    listIterator = table[currentIndex].iterator();
                }
                currentIndex++;
            }
            return true;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new IllegalStateException("There are no more elements to iterate");
            }
            return listIterator.next();
        }
    }
}

