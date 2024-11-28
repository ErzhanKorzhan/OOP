package org.example;

import java.util.List;

import static org.example.FileSubstringSearch.find;

public class Main {
    public static void main(String[] args) {
        List<Integer> positions = find("input.txt", "абра");
        System.out.println(positions);
    }
}