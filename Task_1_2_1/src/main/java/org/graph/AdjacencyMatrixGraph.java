package org.graph;

import org.example.Graph;
import java.io.File;
import java.util.*;

public class AdjacencyMatrixGraph implements Graph {
    private final List<String> vertices;
    private final List<List<Integer>> adjMatrix;

    public AdjacencyMatrixGraph() {
        this.vertices = new ArrayList<>();
        this.adjMatrix = new ArrayList<>();
    }

    @Override
    public void addVertex(String vertex) {
        vertices.add(vertex);
        for (List<Integer> row : adjMatrix) {
            row.add(0);
        }
        adjMatrix.add(new ArrayList<>(Collections.nCopies(vertices.size(), 0)));
    }

    @Override
    public void removeVertex(String vertex) {
        int index = vertices.indexOf(vertex);
        adjMatrix.remove(index);
        for (List<Integer> row : adjMatrix) {
            row.remove(index);
        }
        vertices.remove(index);
    }

    @Override
    public void addEdge(String u, String v) {
        adjMatrix.get(vertices.indexOf(u)).set(vertices.indexOf(v), 1);
    }

    @Override
    public void removeEdge(String u, String v) {
        adjMatrix.get(vertices.indexOf(u)).set(vertices.indexOf(v), 0);
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        int index = vertices.indexOf(vertex);
        List<String> neighbors = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            if (adjMatrix.get(index).get(i) == 1) {
                neighbors.add(vertices.get(i));
            }
        }
        return neighbors;
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
        return vertices.equals(((AdjacencyMatrixGraph) obj).vertices) && adjMatrix.equals(((AdjacencyMatrixGraph) obj).adjMatrix);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        for (List<Integer> row : adjMatrix) {
            tmp.append("\n").append(row);
        }
        return "Vertices: " + vertices + "\nAdjacency Matrix:" + tmp;
    }

    @Override
    public List<String> topologicalSort() {
        Map<String, Integer> inDegree = new HashMap<>();
        for (String vertex : vertices) {
            inDegree.put(vertex, 0);
        }

        for (List<Integer> matrix : adjMatrix) {
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(j) == 1) {
                    String neighbor = vertices.get(j);
                    inDegree.put(neighbor, inDegree.get(neighbor) + 1);
                }
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
            int currentIndex = vertices.indexOf(current);
            for (int i = 0; i < adjMatrix.get(currentIndex).size(); i++) {
                if (adjMatrix.get(currentIndex).get(i) == 1) {
                    String neighbor = vertices.get(i);
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        if (sortedList.size() != vertices.size()) {
            throw new RuntimeException("The graph has a cycle");
        }

        return sortedList;
    }
}