import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Vertex{
    int key;
    int distance;
    int colour = -1;
    Vertex previous;

    private Set<Vertex> neighbours;
    List<Edge> edges = new LinkedList<Edge>();

    public int compareTo(Vertex other){
        return Integer.compare(this.distance, other.distance);
    }

    public boolean isColoured(){
        return colour != -1;
    }

    public int getColour(){
        return colour;
    }

    public void setColour(int colour){
        this.colour = colour;
    }

    public Set<Vertex> getNeighbours(){
        return neighbours;
    }

    Vertex(){
        distance = Integer.MAX_VALUE;
    }

}