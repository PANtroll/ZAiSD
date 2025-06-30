package pl.zaisd.graph;

import java.util.*;

public class Graph {

    private final List<Node> nodes = new ArrayList<>();

    public Graph(int size) {
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            nodes.add(new Node(i, new ArrayList<>()));
        }
        for (Node node : nodes) {
            for (Node node2 : nodes) {
                if (node.id() != node2.id()) {
                    node.addEdge(node2, r.nextInt(29) + 1);
                }
            }
        }
    }

    public int size() {
        return nodes.size();
    }

    public Graph() {

        for (int i = 0; i < 5; i++) {
            nodes.add(new Node(i, new ArrayList<>()));
        }
        List<Integer> list = Arrays.asList(
                    10,  9,  8,  4,
                11,      9,  4,  2,
                 8,  3,      7, 18,
                12, 17, 14,     15,
                13, 20, 11, 16
        );
        Iterator<Integer> iterator = list.iterator();
        for (Node node : nodes) {
            for (Node node2 : nodes) {
                if (node.id() != node2.id()) {
                    node.addEdge(node2, iterator.next());
                }
            }
        }

    }

    public List<Node> getNodes() {
        return nodes;
    }
}
