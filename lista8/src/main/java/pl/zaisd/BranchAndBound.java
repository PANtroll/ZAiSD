package pl.zaisd;

import pl.zaisd.graph.Edge;
import pl.zaisd.graph.Graph;
import pl.zaisd.graph.Node;

import java.util.HashSet;
import java.util.Set;

public class BranchAndBound implements Algorithm {

    @Override
    public int search(Graph graph, int startPoint) {
        int bestCost = Integer.MAX_VALUE;
        Set<Node> visited = new HashSet<>();
        Node startNode = graph.getNodes().get(startPoint);
        visited.add(startNode);
        bestCost = dfs(startNode, startNode, visited, 0, 1, graph.size(), bestCost);
        return bestCost;
    }

    private int dfs(Node current, Node start, Set<Node> visited, int currentCost, int depth, int totalNodes,
                  int bestCost) {
        if (currentCost >= bestCost) return bestCost;

        if (depth == totalNodes) {
            int returnCost = current.getEdge(start).weight();
            if (returnCost < Integer.MAX_VALUE) {
                return Math.min(bestCost, currentCost + returnCost);
            }
            return bestCost;
        }

        for (Edge edge : current.neighbours()) {
            if (!visited.contains(edge.neighbour())) {
                visited.add(edge.neighbour());
                bestCost = dfs(edge.neighbour(), start, visited, currentCost + edge.weight(), depth + 1, totalNodes,
                        bestCost);
                visited.remove(edge.neighbour());
            }
        }
        return bestCost;
    }

    @Override
    public String toString() {
        return "BranchAndBound";
    }
}
