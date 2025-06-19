package pl.zaisd;

import pl.zaisd.graph.Edge;
import pl.zaisd.graph.GraphRequest;
import pl.zaisd.graph.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFord implements Algorithm {
    @Override
    public int search(List<Node> graph, GraphRequest request) {
        Map<Node, Integer> costs = new HashMap<>();//cost of nodes
        for (Node node : graph) {
            if (node.equals(request.source())) {
                costs.put(node, 0);
            } else {
                costs.put(node, Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < graph.size(); i++) {
            boolean update = false;
            for (Node node : graph) {
                for (Edge edge : node.neighbours()) {
                    int distance = costs.get(node) + edge.weight();
                    if (costs.get(node) != Integer.MAX_VALUE &&
                            distance < costs.get(edge.neighbour())) {
                        costs.replace(edge.neighbour(), distance);
                        update = true;
                    }
                }
            }
            if (!update) break;
        }
        return costs.get(request.destination());
    }

    @Override
    public String toString() {
        return "BellmanFord";
    }
}
