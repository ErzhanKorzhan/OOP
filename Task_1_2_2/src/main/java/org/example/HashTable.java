package org.example;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.Objects;
import java.util.ConcurrentModificationException;

public class HashTable<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<Entry<K, V>>[] table;
    private final int capacity;
    private int modCount;

    public HashTable() {
        this.capacity = 100000;
        this.table = new LinkedList[capacity];
        this.modCount = 0;
    }

    private int getIndex(K key) {
        return (key == null ? 0 : Math.abs(key.hashCode())) % capacity;
    }

    // Метод для добавления пары ключ-значение
    public void put(K key, V value) {
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        table[index].add(new Entry<>(key, value));
        modCount++;
    }

    // Метод для получения значения по ключу
    public V get(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    // Метод для удаления пары ключ-значение
    public void remove(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            Iterator<Entry<K, V>> iterator = table[index].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (entry.key.equals(key)) {
                    iterator.remove();
                    modCount++;
                    return;
                }
            }
        }
    }

    // Метод для проверки наличия значения по ключу
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    // Метод для обновления значения по ключу
    public void update(K key, V newValue) {
        int index = getIndex(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    entry.value = newValue;
                    return;
                }
            }
        }
    }

    // итератор с проверкой на модификацию
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int currentIndex = -1;
            private Iterator<Entry<K, V>> listIterator;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (listIterator == null || !listIterator.hasNext()) {
                    currentIndex++;
                    if (currentIndex >= table.length) {
                        return false;
                    }
                    if (table[currentIndex] != null) {
                        listIterator = table[currentIndex].iterator();
                    }
                }
                return true;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new ConcurrentModificationException();
                }
                return listIterator.next();
            }
        };
    }

    // Метод для сравнения с другой хеш-таблицей
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HashTable<K, V> other = (HashTable<K, V>) obj;
        for (int i = 0; i < table.length; i++) {
            LinkedList<Entry<K, V>> list = table[i];
            LinkedList<Entry<K, V>> otherList = other.table[i];
            if (!Objects.equals(list, otherList)) {
                return false;
            }
        }
        return true;
    }

    // Метод для преобразования в строку
    public void outString() {
        boolean flag = false;
        System.out.print("{");
        for (LinkedList<Entry<K, V>> entries : table) {
            if (entries != null) {
                for (Entry<K, V> entry : entries) {
                    if (!flag) {
                        System.out.print(entry.key+"="+entry.value);
                        flag = true;
                    }
                    else{
                        System.out.print(", "+entry.key+"="+entry.value);
                    }
                }
            }
        }
        System.out.print("}");
    }
}