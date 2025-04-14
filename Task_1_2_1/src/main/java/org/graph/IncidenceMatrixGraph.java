package org.graph;

import org.example.Graph;
import java.io.File;
import java.util.*;

public class IncidenceMatrixGraph implements Graph {
    private final List<String> vertices;
    private final List<List<Integer>> incidenceMatrix;
    private int edgeCount;

    public IncidenceMatrixGraph() {
        this.vertices = new ArrayList<>();
        this.incidenceMatrix = new ArrayList<>();
        this.edgeCount = 0;
    }

    @Override
    public void addVertex(String vertex) {
        vertices.add(vertex);
        List<Integer> newVertexRow = new ArrayList<>(Collections.nCopies(edgeCount, 0));
        incidenceMatrix.add(newVertexRow);
    }

    @Override
    public void removeVertex(String vertex) {
        incidenceMatrix.remove(vertices.indexOf(vertex));
        vertices.remove(vertex);
    }

    @Override
    public void addEdge(String u, String v) {
        for (List<Integer> row : incidenceMatrix) {
            row.add(0);
        }
        incidenceMatrix.get(vertices.indexOf(u)).set(edgeCount, 1);
        incidenceMatrix.get(vertices.indexOf(v)).set(edgeCount, -1);
        edgeCount++;
    }

    @Override
    public void removeEdge(String u, String v) {
        int indexU = vertices.indexOf(u);
        int indexV = vertices.indexOf(v);
        for (int j = 0; j < edgeCount; j++) {
            if (incidenceMatrix.get(indexU).get(j) == 1 && incidenceMatrix.get(indexV).get(j) == -1) {
                edgeCount--;
                for (List<Integer> row : incidenceMatrix) {
                    row.remove(j);
                }
                break;
            }
        }
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        List<String> neighbors = new ArrayList<>();
        int index = vertices.indexOf(vertex);
        for (int j = 0; j < edgeCount; j++) {
            if (incidenceMatrix.get(index).get(j) == 1) {
                for (int i = 0; i < vertices.size(); i++) {
                    if (incidenceMatrix.get(i).get(j) == -1) {
                        neighbors.add(vertices.get(i));
                    }
                }
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
        return vertices.equals(((IncidenceMatrixGraph) obj).vertices) && incidenceMatrix.equals(((IncidenceMatrixGraph) obj).incidenceMatrix);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (List<Integer> row : incidenceMatrix) {
            str.append("\n").append(row);
        }
        return "Vertices: " + vertices + "\nIncidence Matrix:" + str;
    }

    @Override
    public List<String> topologicalSort() {
        Map<String, Integer> inDegree = new HashMap<>();
        for (String vertex : vertices) {
            inDegree.put(vertex, 0);
        }

        for (int edge = 0; edge < edgeCount; edge++) {
            for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++) {
                if (incidenceMatrix.get(vertexIndex).get(edge) == -1) {
                    String toVertex = vertices.get(vertexIndex);
                    inDegree.put(toVertex, inDegree.get(toVertex) + 1);
                    break;
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
            for (int edge = 0; edge < edgeCount; edge++) {
                if (incidenceMatrix.get(currentIndex).get(edge) == 1) {
                    for (int toIndex = 0; toIndex < vertices.size(); toIndex++) {
                        if (incidenceMatrix.get(toIndex).get(edge) == -1) {
                            String toVertex = vertices.get(toIndex);
                            inDegree.put(toVertex, inDegree.get(toVertex) - 1);
                            if (inDegree.get(toVertex) == 0) {
                                queue.add(toVertex);
                            }
                            break;
                        }
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