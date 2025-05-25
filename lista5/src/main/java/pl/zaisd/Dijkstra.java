package pl.zaisd;

import pl.zaisd.graph.Edge;
import pl.zaisd.graph.GraphRequest;
import pl.zaisd.graph.Node;

import java.util.*;

public class Dijkstra implements Algorithm {


    @Override
    public int search(List<Node> graph, GraphRequest request) {
        Map<Node, Integer> costs = new HashMap<>();//weight of nodes
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(costs::get));
        for (Node node : graph) {
            int initCost = Integer.MAX_VALUE;
            if(node.equals(request.source())){
                initCost = 0;
            }
            costs.put(node, initCost);
            pq.offer(node);
        }
        while(!pq.isEmpty()){
            Node current = pq.poll();
            for(Edge edge : current.neighbours()){

                int distance = costs.get(current) + edge.weight();
                if(distance < costs.get(edge.neighbour())){
//                    pq.remove(current);
                    costs.replace(edge.neighbour(), distance);
                    pq.offer(edge.neighbour());
                }
            }
        }

        return costs.get(request.destination());
    }

    @Override
    public String toString() {
        return "Dijkstra";
    }
}
