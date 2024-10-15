package org.example;

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
        int vertexIndex = vertices.indexOf(vertex);
        if (vertexIndex != -1) {
            vertices.remove(vertexIndex);
            incidenceMatrix.remove(vertexIndex);
        }
    }

    @Override
    public void addEdge(String u, String v) {
        int uIndex = vertices.indexOf(u);
        int vIndex = vertices.indexOf(v);
        if (uIndex != -1 || vIndex != -1) {
            for (List<Integer> row : incidenceMatrix) {
                row.add(0);
            }
            incidenceMatrix.get(uIndex).set(edgeCount, 1);
            incidenceMatrix.get(vIndex).set(edgeCount, -1);
            edgeCount++;
        }
    }

    @Override
    public void removeEdge(String u, String v) {
        int uIndex = vertices.indexOf(u);
        int vIndex = vertices.indexOf(v);
        if (uIndex != -1 || vIndex != -1) {
            int edgeIndex = -1;
            for (int j = 0; j < edgeCount; j++) {
                if (incidenceMatrix.get(uIndex).get(j) == 1 && incidenceMatrix.get(vIndex).get(j) == -1) {
                    edgeIndex = j;
                    break;
                }
            }
            if (edgeIndex != -1) {
                edgeCount--;
                for (List<Integer> row : incidenceMatrix) {
                    row.remove(edgeIndex);
                }
            }
        }
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        int index = vertices.indexOf(vertex);
        if (index == -1) return Collections.emptyList();

        List<String> neighbors = new ArrayList<>();
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
        // Для простоты возьму, что файл имеет формат:
        // v <vertex_name> для добавления вершины
        // e <vertex1> <vertex2> для добавления ребра
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
    public boolean equals(Object o) {
        if (!(o instanceof IncidenceMatrixGraph)) return false;
        return vertices.equals(((IncidenceMatrixGraph) o).vertices) && incidenceMatrix.equals(((IncidenceMatrixGraph) o).incidenceMatrix);
    }

    @Override
    public void print_gr() {
        System.out.println("Vertices: " + vertices);
        System.out.println("Incidence Matrix:");
        for (List<Integer> row : incidenceMatrix) {
            System.out.println(row.toString());
        }
    }

    @Override
    public List<String> topologicalSort() {
        Map<String, Integer> inDegree = new HashMap<>();
        for (String vertex : vertices) {
            inDegree.put(vertex, 0);
        }

        for (int edge = 0; edge < edgeCount; edge++) {
            int fromIndex = -1;
            int toIndex = -1;

            for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++) {
                int value = incidenceMatrix.get(vertexIndex).get(edge);
                if (value == 1) {
                    fromIndex = vertexIndex;
                } else if (value == -1) {
                    toIndex = vertexIndex;
                }
            }

            if (toIndex != -1) {
                String toVertex = vertices.get(toIndex);
                inDegree.put(toVertex, inDegree.get(toVertex) + 1);
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