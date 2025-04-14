package org.example;

import java.util.List;

public interface Graph {
    void addVertex(String vertex);
    void removeVertex(String vertex);
    void addEdge(String u, String v);
    void removeEdge(String u, String v);
    List<String> getNeighbors(String vertex);
    void readFromFile(String filename);
    List<String> topologicalSort();
}
