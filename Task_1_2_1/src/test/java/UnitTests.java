import org.example.Graph;
import org.graph.AdjacencyListGraph;
import org.graph.AdjacencyMatrixGraph;
import org.graph.IncidenceMatrixGraph;
import org.junit.jupiter.api.Test;

import java.util.*;

public class UnitTests
{
    Random rnd = new Random();

    @Test
    void ReadTestAdjListTest()
    {
        Graph gr1 = new AdjacencyListGraph();
        Graph gr2 = new AdjacencyListGraph();
        gr1.readFromFile("fl.txt");
        gr2.addVertex("ver3");
        gr2.addVertex("ver1");
        gr2.addVertex("ver2");
        gr2.addEdge("ver2", "ver1");
        gr2.addEdge("ver1", "ver3");
        assert gr1.equals(gr2);
    }

    @Test
    void ReadTestAdjMatrixTest()
    {
        Graph gr1 = new AdjacencyMatrixGraph();
        Graph gr2 = new AdjacencyMatrixGraph();
        gr1.readFromFile("fl.txt");
        gr2.addVertex("ver3");
        gr2.addVertex("ver1");
        gr2.addVertex("ver2");
        gr2.addEdge("ver2", "ver1");
        gr2.addEdge("ver1", "ver3");
        assert gr1.equals(gr2);
    }

    @Test
    void ReadTestIncMatrixTest()
    {
        Graph gr1 = new IncidenceMatrixGraph();
        Graph gr2 = new IncidenceMatrixGraph();
        gr1.readFromFile("fl.txt");
        gr2.addVertex("ver3");
        gr2.addVertex("ver1");
        gr2.addVertex("ver2");
        gr2.addEdge("ver2", "ver1");
        gr2.addEdge("ver1", "ver3");
        assert gr1.equals(gr2);
    }

    @Test
    void AddRemoveVertexIncMatrixTest()
    {
        List<String> ans = new ArrayList<>();
        Graph gr1 = new IncidenceMatrixGraph();
        int addTimes = rnd.nextInt(100);
        for (int i = 0; i < addTimes; ++i){
            ans.add("ver"+i);
            gr1.addVertex("ver"+i);
        }
        int remTimes = rnd.nextInt(addTimes);
        for (int i = 0; i < remTimes; ++i){
            ans.remove("ver"+i);
            gr1.removeVertex("ver"+i);
        }
        List<String> ex = gr1.topologicalSort();
        for (String s : ex) {
            boolean tmp = false;
            for (String an : ans) {
                if (Objects.equals(an, s)) {
                    tmp = true;
                    break;
                }
            }
            assert tmp;
        }
        assert ans.size() == ex.size();
    }

    @Test
    void AddRemoveVertexAdjListTest()
    {
        List<String> ans = new ArrayList<>();
        Graph gr1 = new AdjacencyListGraph();
        int addTimes = rnd.nextInt(100);
        for (int i = 0; i < addTimes; ++i){
            ans.add("ver"+i);
            gr1.addVertex("ver"+i);
        }
        int remTimes = rnd.nextInt(addTimes);
        for (int i = 0; i < remTimes; ++i){
            ans.remove("ver"+i);
            gr1.removeVertex("ver"+i);
        }
        List<String> ex = gr1.topologicalSort();
        for (String s : ex) {
            boolean tmp = false;
            for (String an : ans) {
                if (Objects.equals(an, s)) {
                    tmp = true;
                    break;
                }
            }
            assert tmp;
        }
        assert ans.size() == ex.size();
    }

    @Test
    void AddRemoveVertexAdjMatrixTest()
    {
        List<String> ans = new ArrayList<>();
        Graph gr1 = new AdjacencyMatrixGraph();
        int addTimes = rnd.nextInt(100);
        for (int i = 0; i < addTimes; ++i){
            ans.add("ver"+i);
            gr1.addVertex("ver"+i);
        }
        int remTimes = rnd.nextInt(addTimes);
        for (int i = 0; i < remTimes; ++i){
            ans.remove("ver"+i);
            gr1.removeVertex("ver"+i);
        }
        List<String> ex = gr1.topologicalSort();
        for (String s : ex) {
            boolean tmp = false;
            for (String an : ans) {
                if (Objects.equals(an, s)) {
                    tmp = true;
                    break;
                }
            }
            assert tmp;
        }
        assert ans.size() == ex.size();
    }

    @Test
    void equalsAdjListAdjMatrixTest()
    {
        Graph gr1 = new AdjacencyListGraph();
        Graph gr2 = new AdjacencyMatrixGraph();
        gr1.readFromFile("fl.txt");
        gr2.readFromFile("fl.txt");
        List<String> ex1 = gr1.topologicalSort();
        List<String> ex2 = gr2.topologicalSort();
        for (String s : ex1) {
            boolean tmp = false;
            for (String an : ex2) {
                if (Objects.equals(an, s)) {
                    tmp = true;
                    break;
                }
            }
            assert tmp;
        }
        assert ex2.size() == ex1.size();
    }

    @Test
    void equalsAdjListIncMatrixTest()
    {
        Graph gr1 = new AdjacencyListGraph();
        Graph gr2 = new IncidenceMatrixGraph();
        gr1.readFromFile("fl.txt");
        gr2.readFromFile("fl.txt");
        List<String> ex1 = gr1.topologicalSort();
        List<String> ex2 = gr2.topologicalSort();
        for (String s : ex1) {
            boolean tmp = false;
            for (String an : ex2) {
                if (Objects.equals(an, s)) {
                    tmp = true;
                    break;
                }
            }
            assert tmp;
        }
        assert ex2.size() == ex1.size();
    }

    @Test
    void AdjListStringTest()
    {
        Graph gr1 = new AdjacencyListGraph();
        gr1.readFromFile("fl.txt");
        assert gr1.toString().equals("Adjacency List: {ver3=[], ver2=[ver1], ver1=[ver3]}");
    }

    @Test
    void AdjMatrixStringTest()
    {
        Graph gr1 = new AdjacencyMatrixGraph();
        gr1.readFromFile("fl.txt");
        assert gr1.toString().equals("Vertices: [ver3, ver1, ver2]\n" +
                                     "Adjacency Matrix:\n" +
                                     "[0, 0, 0]\n" +
                                     "[1, 0, 0]\n" +
                                     "[0, 1, 0]");
    }

    @Test
    void IncMatrixStringTest()
    {
        Graph gr1 = new IncidenceMatrixGraph();
        gr1.readFromFile("fl.txt");
        assert gr1.toString().equals("Vertices: [ver3, ver1, ver2]\n" +
                                     "Incidence Matrix:\n" +
                                     "[0, -1]\n" +
                                     "[-1, 1]\n" +
                                     "[1, 0]");
    }

    @Test
    void NeighboursAdjMatrixTest()
    {
        List<String> ans = new ArrayList<>();
        ans.add("ver3");
        Graph gr1 = new AdjacencyMatrixGraph();
        gr1.readFromFile("fl.txt");
        assert gr1.getNeighbors("ver1").equals(ans);
    }

    @Test
    void NeighboursAdjListTest()
    {
        List<String> ans = new ArrayList<>();
        ans.add("ver3");
        Graph gr1 = new AdjacencyListGraph();
        gr1.readFromFile("fl.txt");
        assert gr1.getNeighbors("ver1").equals(ans);
    }

    @Test
    void NeighboursIncMatrixTest()
    {
        List<String> ans = new ArrayList<>();
        ans.add("ver3");
        Graph gr1 = new IncidenceMatrixGraph();
        gr1.readFromFile("fl.txt");
        assert gr1.getNeighbors("ver1").equals(ans);
    }

    @Test
    void AddRemoveEdgeIncMatrixTest()
    {
        List<String> ans = new ArrayList<>();
        Graph gr1 = new IncidenceMatrixGraph();
        gr1.addVertex("ver1");
        gr1.addVertex("ver2");
        gr1.addEdge("ver1", "ver2");
        ans.add("ver2");
        assert gr1.getNeighbors("ver1").equals(ans);
        gr1.removeEdge("ver1", "ver2");
        ans.remove("ver2");
        assert gr1.getNeighbors("ver1").equals(ans);
    }

    @Test
    void AddRemoveEdgeAdjListTest()
    {
        List<String> ans = new ArrayList<>();
        Graph gr1 = new AdjacencyListGraph();
        gr1.addVertex("ver1");
        gr1.addVertex("ver2");
        gr1.addEdge("ver1", "ver2");
        ans.add("ver2");
        assert gr1.getNeighbors("ver1").equals(ans);
        gr1.removeEdge("ver1", "ver2");
        ans.remove("ver2");
        assert gr1.getNeighbors("ver1").equals(ans);
    }

    @Test
    void AddRemoveEdgeAdgMatrixTest()
    {
        List<String> ans = new ArrayList<>();
        Graph gr1 = new AdjacencyMatrixGraph();
        gr1.addVertex("ver1");
        gr1.addVertex("ver2");
        gr1.addEdge("ver1", "ver2");
        ans.add("ver2");
        assert gr1.getNeighbors("ver1").equals(ans);
        gr1.removeEdge("ver1", "ver2");
        ans.remove("ver2");
        assert gr1.getNeighbors("ver1").equals(ans);
    }

    @Test
    void TopologicalSortIncMatrixTest()
    {
        List<String> ans = new ArrayList<>();
        Graph gr1 = new IncidenceMatrixGraph();
        gr1.addVertex("ver1");
        gr1.addVertex("ver2");
        gr1.addEdge("ver1", "ver2");
        ans.add("ver1");
        ans.add("ver2");
        assert gr1.topologicalSort().equals(ans);
    }

    @Test
    void TopologicalSortAdjMatrixTest()
    {
        List<String> ans = new ArrayList<>();
        Graph gr1 = new AdjacencyMatrixGraph();
        gr1.addVertex("ver1");
        gr1.addVertex("ver2");
        gr1.addEdge("ver1", "ver2");
        ans.add("ver1");
        ans.add("ver2");
        assert gr1.topologicalSort().equals(ans);
    }

    @Test
    void TopologicalSortAdjListTest()
    {
        List<String> ans = new ArrayList<>();
        Graph gr1 = new AdjacencyListGraph();
        gr1.addVertex("ver1");
        gr1.addVertex("ver2");
        gr1.addEdge("ver1", "ver2");
        ans.add("ver1");
        ans.add("ver2");
        assert gr1.topologicalSort().equals(ans);
    }
}