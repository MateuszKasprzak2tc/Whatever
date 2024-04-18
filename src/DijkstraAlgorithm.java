import java.util.*;



public class DijkstraAlgorithm {
    public static int dijkstra(Graph graph, Vertex source, Vertex destination){
        Set<Vertex> unvisited = new HashSet<>(graph.vertices);
        source.distance = 0;

        while(!unvisited.isEmpty()){
            Vertex current = getMinimumDistanceVertex(unvisited);
            System.out.println(current.key);
            unvisited.remove(current);
            for (Edge edge : current.edges){
                Vertex neighbour = edge.vertex;
                int newDistance = current.distance + edge.weight;
                if(newDistance < neighbour.distance){
                    neighbour.distance = newDistance;
                    neighbour.previous = current;
                }
            }

            System.out.print(graph.findVertex(1).distance);
            System.out.print(" ");
            System.out.print(graph.findVertex(2).distance);
            System.out.print(" ");
            System.out.print(graph.findVertex(3).distance);
            System.out.print(" ");
            System.out.print(graph.findVertex(4).distance);
            System.out.print(" ");
            System.out.print(graph.findVertex(5).distance);
            System.out.print(" ");
            System.out.print(graph.findVertex(6).distance);
            System.out.print(" ");
            System.out.print(graph.findVertex(7).distance);
            System.out.print(" ");
            System.out.print(graph.findVertex(8).distance);
            System.out.print(" ");
            System.out.print(graph.findVertex(9).distance);
            System.out.println();
        }

        return destination.distance;
    }

    private static Vertex getMinimumDistanceVertex(Set<Vertex> vertices){
        Vertex minVertex = null;
        int minDistance = Integer.MAX_VALUE;
        for( Vertex vertex : vertices ){
            if ( vertex.distance < minDistance ){
                minVertex = vertex;
                minDistance = vertex.distance;
            }
        }
        return minVertex;
    }

    public static List<Vertex> getShorterPathTo(Vertex target){
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = target; vertex !=null; vertex = vertex.previous){
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }



}
