package pl.zaisd.graph;

import java.util.List;

public record Node(int id, List<Edge> neighbours) {

    public Edge addEdge(Node newNeighbour, int weight){
        Edge newEdge = new Edge(newNeighbour, weight);
        neighbours.add(newEdge);
        return newEdge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node: ").append(id).append("\t").append("neighbours: {\n");
        neighbours.forEach(nbr -> sb.append("\t").append(nbr.neighbour().id()).append(" weight:").append(nbr.weight()).append("\n"));
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
