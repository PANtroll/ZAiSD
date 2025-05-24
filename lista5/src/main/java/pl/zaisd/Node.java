package pl.zaisd;

import java.util.List;

public record Node(List<Edge> neighbours) {

    public Edge addEdge(Node newNeighbour, int weight){
        Edge newEdge = new Edge(newNeighbour, weight);
        neighbours.add(newEdge);
        return newEdge;
    }

}
