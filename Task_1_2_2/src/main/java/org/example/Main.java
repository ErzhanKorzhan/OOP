package org.example;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Number> hashTable1 = new HashTable<>();
        HashTable<String, Number> hashTable2 = new HashTable<>();
        hashTable1.put("one", 1);
        hashTable1.update("one", 1.0);
        hashTable1.update("one", 3.0);
        hashTable2.put("one", 3.0);
        System.out.println(hashTable1.equals(hashTable2));
    }
}
