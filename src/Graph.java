import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Graph {

    List<Vertex> vertices  = new LinkedList<Vertex>();
    public void addVertex(int key){
        Vertex newVertex = new Vertex();
        newVertex.key = key;
        vertices.add(newVertex);

    }
    public void addEdge(int key1, int key2, int weight){
        Vertex vertex1 = null;
        Vertex vertex2 = null;
        for(Vertex vertex : vertices){
            if (vertex.key == key1){
                vertex1 = vertex;
            }
            if (vertex.key == key2){
                vertex2 = vertex;
            }
        }

        if (vertex1 == null || vertex2 == null){
            System.out.printf("nie ma jednego lub wszystkich verteksow");
            return;
        }

        Edge newEdge = new Edge();
        newEdge.vertex = vertex1;
        newEdge.destination = vertex2;
        newEdge.weight = weight;
        vertex1.edges.add(newEdge);


    }
    public Vertex findVertex(int key) {
        for (Vertex vertex : vertices) {
            if (vertex.key == key) {
                return vertex;
            }
        }
        return null;
    }

    public void deleteVertex(int key){
        Vertex vertexToRemove = findVertex(key);
        if(vertexToRemove != null){
            vertices.remove(vertexToRemove);
        }else {
            System.out.printf("nie ma takiego wierzcholka");
        }

        for (Vertex vertex : vertices){
            Iterator<Edge> iterator = vertex.edges.iterator();
            while (iterator.hasNext()){
                Edge edge = iterator.next();
                if (edge.vertex == vertexToRemove){
                    iterator.remove();
                }
            }
        }

    }
    @Test
    void shouldDelete(){
        Graph graph = new Graph();
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1,2,3);

        graph.deleteVertex(1);

        assert graph.vertices.size() == 1;


    }

   public void deleteEdge(int key1, int key2){
       Vertex vertex1 = null;
       Vertex vertex2 = null;
       for(Vertex vertex : vertices){
           if (vertex.key == key1){
               vertex1 = vertex;
           }
           if (vertex.key == key2){
               vertex2 = vertex;
           }
       }

       if (vertex1 == null || vertex2 == null){
           System.out.printf("nie ma jednego lub wszystkich verteksow");
           return;
       }
        for(Edge edge : vertex1.edges){
            if (edge.vertex == vertex2){
                vertex1.edges.remove(edge);
                return;
            }
            System.out.printf("Edge don't exist");

        }
   }

   public int kruskal(){
        int totalWeight = 0;
        List<Edge> allEdges = new ArrayList<>();

        for (Vertex vertex : vertices){
            allEdges.addAll(vertex.edges);
        }

       System.out.println(allEdges.size());

        allEdges.sort(Comparator.comparing(e -> e.weight));

        for(Edge edge: allEdges){
            System.out.print(edge.weight);
            System.out.print(" ");
        }
       System.out.println("");

        Set<Integer> tree = new HashSet<>();

        while (tree.size() != this.vertices.size()){
            Edge e = allEdges.remove(0);
            if(tree.contains(e.vertex.key) && tree.contains(e.destination.key)){
                continue;
            }
            tree.add(e.vertex.key);
            tree.add(e.destination.key);
            totalWeight += e.weight;
        }

//        Set<Vertex> visitedVertices = new HashSet<>();
//
//        for (Edge edge : allEdges){
//            Vertex source = edge.vertex;
//            Vertex destination = edge.destination;
//            if( visitedVertices.contains(source) || !visitedVertices.contains(destination)){
//                totalWeight += edge.weight;
//                System.out.println(String.valueOf(totalWeight));
//                visitedVertices.add(source);
//                visitedVertices.add(destination);
//            }
//        }
//
          return totalWeight;
   }

//    public int prim() {
//        int totalWeight = 0;
//        Set<Vertex> visitedVertices = new HashSet<>();
//        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
//
//        Vertex startVertex = vertices.get(0);
//        visitedVertices.add(startVertex);
//
//        for (Edge edge :startVertex.edges){
//            priorityQueue.add(edge);
//        }
//
//        while(!priorityQueue.isEmpty() && visitedVertices.size() < vertices.size()){
//            Edge minEdge
//        }
//    }

   @BeforeEach
    void inniTest(){
       for(int i = 0; i < 8; i++){
           addVertex(i);

       };
       addEdge(4,6,1);//
       addEdge(4,5,2);//
       addEdge(2,7,3);//
       addEdge(0,6,3);//
       addEdge(2,4,4);//
       addEdge(0,1,5);//
       addEdge(2,6,5);//
       addEdge(1,5,6);//
       addEdge(5,6,6);//
       addEdge(1,7,7);//
       addEdge(1,4,8);//
       addEdge(3,6,8);//
       addEdge(0,3,9);//
       addEdge(1,2,9);
       addEdge(2,3,9);
       addEdge(6,7,9);
   }
   @Test
    void shouldKrusal(){

           int actual = kruskal();
           int expected = 26;
           Assertions.assertEquals(expected, actual);

   }

    public Map<Vertex,Integer> greedyColoring(){
        if(vertices.isEmpty()){
            System.out.println("Empty graph.");
            return null;
        }

        Map<Vertex, Integer> vertexColours = new HashMap<>();
        Set<Integer> usedColours = new HashSet<>();

        for(Vertex vertex : vertices){
            Set<Integer> neighbourColours = new HashSet<>();
            for(Vertex neighbour : vertex.getNeighbours()){
                if(vertexColours.containsKey(neighbour)){
                    neighbourColours.add(vertexColours.get(neighbour));
                }
            }
            int colour = findSmallestAvailableColour(usedColours, neighbourColours);
            vertexColours.put(vertex, colour);
            usedColours.add(colour);
        }

        System.out.println("Number of colors used: " + usedColours.size());
        return vertexColours;
    }

    private int findSmallestAvailableColour(Set<Integer> usedColours, Set<Integer> neighbourColours) {
        for(int colour = 0; ; colour++){
            if(!usedColours.contains(colour) && !neighbourColours.contains(colour)){
                return colour;
            }
        }
    }


    @Test
    void shouldColour(){

        Graph graph = new Graph();

        for(int i = 0; i <= 5; i++){
            graph.addVertex(i);
        }
        graph.addEdge(1,2,0);
        graph.addEdge(1,3,0);
        graph.addEdge(2,3,0);
        graph.addEdge(2,4,0);
        graph.addEdge(3,5,0);
        graph.addEdge(4,5,0);


        Map<Vertex, Integer> vertexColours = graph.greedyColoring();

        Assertions.assertNotNull(vertexColours);

        Assertions.assertEquals(5, vertexColours.size());

        for(Vertex vertex : vertices){
            int colour = vertexColours.get(vertex);
            for (Vertex neighbour : vertex.getNeighbours()){
                int neighbourColour = vertexColours.get(neighbour);

                Assertions.assertNotEquals(colour, neighbourColour, "should have diffrent colour");
            }
        }

    }

}
