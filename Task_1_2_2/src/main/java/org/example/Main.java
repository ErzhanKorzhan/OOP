package org.example;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.update("one", 1.0);
        hashTable.update("one", 3.0);
        System.out.println(hashTable.get("one"));
    }
}
