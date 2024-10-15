package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph gr = new IncidenceMatrixGraph();
        gr.readFromFile("fl.txt");
        gr.print_gr();
        List<String> a = gr.topologicalSort();
        System.out.println(a);
    }
}