package pl.zaisd;

import pl.zaisd.graph.Graph;
import pl.zaisd.graph.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteForce implements Algorithm{

    @Override
    public int search(Graph graph, int startPoint) {
        List<Node> toPermute = new ArrayList<>(graph.getNodes());
        toPermute.remove(startPoint);
        Node startNode = graph.getNodes().get(startPoint);
        int minCost = Integer.MAX_VALUE;

        List<List<Node>> permutations = generatePermutations(toPermute, 0);

        for (List<Node> perm : permutations) {
            int cost = 0;
            Node current = startNode;
            for (Node next : perm) {
                cost += current.getEdge(next).weight();
                current = next;
            }
            cost += current.getEdge(startNode).weight();
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    private List<List<Node>> generatePermutations(List<Node> list, int start) {
        List<List<Node>> result = new ArrayList<>();
        if (start == list.size()) {
            result.add(new ArrayList<>(list));
            return result;
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, i, start);
            result.addAll(generatePermutations(list, start + 1));
            Collections.swap(list, i, start);
        }
        return result;
    }

}
