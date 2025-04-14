package org.graph;

import org.example.Graph;
import java.io.File;
import java.util.*;

public class AdjacencyListGraph implements Graph {
    private final Map<String, List<String>> adjList;

    public AdjacencyListGraph() {
        this.adjList = new HashMap<>();
    }

    @Override
    public void addVertex(String vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void removeVertex(String vertex) {
        adjList.values().forEach(e -> e.remove(vertex));
        adjList.remove(vertex);
    }

    @Override
    public void addEdge(String u, String v) {
        adjList.get(u).add(v);
    }

    @Override
    public void removeEdge(String u, String v) {
        adjList.get(u).remove(v);
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    @Override
    public void readFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts[0].equals("v")) {
                    addVertex(parts[1]);
                } else if (parts[0].equals("e")) {
                    addEdge(parts[1], parts[2]);
                }
            }
        } catch (Exception e) {
            System.out.println("File Problem");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return adjList.equals(((AdjacencyListGraph) obj).adjList);
    }

    @Override
    public String toString() {
        return "Adjacency List: " + adjList;
    }

    @Override
    public List<String> topologicalSort() {
        Map<String, Integer> inDegree = new HashMap<>();
        for (String vertex : adjList.keySet()) {
            inDegree.put(vertex, 0);
        }

        for (List<String> neighbors : adjList.values()) {
            for (String neighbor : neighbors) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for (String vertex : inDegree.keySet()) {
            if (inDegree.get(vertex) == 0) {
                queue.add(vertex);
            }
        }

        List<String> sortedList = new ArrayList<>();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            sortedList.add(current);

            for (String neighbor : adjList.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (sortedList.size() != adjList.size()) {
            throw new RuntimeException("The graph has a cycle");
        }

        return sortedList;
    }
}