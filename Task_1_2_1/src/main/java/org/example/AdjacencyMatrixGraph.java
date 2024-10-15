package org.example;

import java.io.File;
import java.util.*;

public class AdjacencyMatrixGraph implements Graph {
    private final List<String> vertices;
    private int[][] adjMatrix;

    public AdjacencyMatrixGraph() {
        this.vertices = new ArrayList<>();
        this.adjMatrix = new int[0][0];
    }

    @Override
    public void addVertex(String vertex) {
        vertices.add(vertex);
        int[][] newMatrix = new int[vertices.size()][vertices.size()];
        for (int i = 0; i < adjMatrix.length; i++) {
            System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, adjMatrix.length);
        }
        adjMatrix = newMatrix;
    }

    @Override
    public void removeVertex(String vertex) {
        int id = vertices.indexOf(vertex);
        if (id != -1) {
            vertices.remove(id);
            int[][] newMatrix = new int[vertices.size()][vertices.size()];
            for (int i = 0, I = 0; i < adjMatrix.length; i++) {
                if (i == id) continue;
                for (int j = 0, J = 0; j < adjMatrix.length; j++) {
                    if (j == id) continue;
                    newMatrix[I][J] = adjMatrix[i][j];
                    J++;
                }
                I++;
            }
            adjMatrix = newMatrix;
        }
    }

    @Override
    public void addEdge(String u, String v) {
        int uIndex = vertices.indexOf(u);
        int vIndex = vertices.indexOf(v);
        if (uIndex != -1 && vIndex != -1) {
            adjMatrix[uIndex][vIndex] = 1;
        }
    }

    @Override
    public void removeEdge(String u, String v) {
        int uIndex = vertices.indexOf(u);
        int vIndex = vertices.indexOf(v);
        if (uIndex != -1 && vIndex != -1) {
            adjMatrix[uIndex][vIndex] = 0;
        }
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        int index = vertices.indexOf(vertex);
        List<String> neighbors = new ArrayList<>();
        if (index != -1) {
            for (int i = 0; i < vertices.size(); i++) {
                if (adjMatrix[index][i] == 1) {
                    neighbors.add(vertices.get(i));
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
        if (!(o instanceof AdjacencyMatrixGraph)) return false;
        return vertices.equals(((AdjacencyMatrixGraph) o).vertices) && Arrays.deepEquals(adjMatrix, ((AdjacencyMatrixGraph) o).adjMatrix);
    }

    @Override
    public void print_gr() {
        System.out.println("Vertices: "+vertices);
        System.out.println("Adjacency Matrix:");
        for (int[] row : adjMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    @Override
    public List<String> topologicalSort() {
        Map<String, Integer> inDegree = new HashMap<>();
        for (String vertex : vertices) {
            inDegree.put(vertex, 0);
        }

        for (int[] matrix : adjMatrix) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (matrix[j] == 1) {
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
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[currentIndex][i] == 1) {
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