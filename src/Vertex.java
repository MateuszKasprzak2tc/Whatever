import java.util.LinkedList;
import java.util.List;

public class Vertex{
    int key;
    int distance;

    Vertex previous;
    List<Edge> edges = new LinkedList<Edge>();

    public int compareTo(Vertex other){
        return Integer.compare(this.distance, other.distance);
    }

    Vertex(){
        distance = Integer.MAX_VALUE;
    }

}