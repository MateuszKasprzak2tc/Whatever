import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class Test {
    Graph graph;
    Vertex source;
    Vertex destination;
    @BeforeEach
    void initTest(){
        graph = new Graph();
        for(int i = 1; i < 10; i++){
            graph.addVertex(i);
        }
        graph.addEdge(1,2,2);
        graph.addEdge(1,3,5);
        graph.addEdge(1,4,2);
        graph.addEdge(2,3,3);
        graph.addEdge(2,5,1);
        graph.addEdge(5,3,1);
        graph.addEdge(5,9,7);
        graph.addEdge(9,8,1);
        graph.addEdge(8,3,1);
        graph.addEdge(8,6,3);
        graph.addEdge(6,3,1);
        graph.addEdge(6,7,2);
        graph.addEdge(7,4,2);
        graph.addEdge(4,3,3);

        source = graph.findVertex(1);
        destination = graph.findVertex(9);
    }
    @org.junit.jupiter.api.Test
    void algorithmShouldWork(){
        int actual = DijkstraAlgorithm.dijkstra(graph, source, destination);
        int expected = 6;
        Assertions.assertEquals(expected, actual);
    }
}
