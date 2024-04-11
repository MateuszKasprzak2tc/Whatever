import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
        newEdge.vertex = vertex2;
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

}
