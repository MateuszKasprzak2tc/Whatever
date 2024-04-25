import java.util.*;

public class GraphColoring {
    private Map<Integer, List<Integer>> adjacencyList;

    public GraphColoring(){
        adjacencyList = new HashMap<>();
    }
    public void addVertex(int vertex){
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(int vertex, int destination){
        adjacencyList.get(vertex).add(destination);
        adjacencyList.get(destination).add(vertex);

    }


}
