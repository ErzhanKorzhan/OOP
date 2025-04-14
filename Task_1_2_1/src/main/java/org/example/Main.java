package org.example;

import org.graph.AdjacencyMatrixGraph;

public class Main {
    public static void main(String[] args) {
        Graph gr = new AdjacencyMatrixGraph();
        gr.readFromFile("fl.txt");
        System.out.println(gr);
    }
}