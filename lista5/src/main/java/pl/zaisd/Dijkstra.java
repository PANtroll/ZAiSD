package pl.zaisd;

import pl.zaisd.graph.Edge;
import pl.zaisd.graph.GraphRequest;
import pl.zaisd.graph.Node;

import java.util.*;

public class Dijkstra implements Algorithm {


    @Override
    public int search(List<Node> graph, GraphRequest request) {
        int cost = 0;
        Map<Node, Integer> map = new HashMap<>();//weight of nodes
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Node node : graph) {
            if(node.equals(request.source())){
                map.put(node, 0);
            }
            else{
                map.put(node, Integer.MAX_VALUE);
            }
            pq.add(node);
        }
        while(!pq.isEmpty()){
            Node current = pq.poll();
            for(Edge edge : current.neighbours()){
                int distance = map.get(current) + edge.weight();
                if(distance < map.get(edge.neighbour())){
                    pq.remove(edge.neighbour());
                    map.replace(edge.neighbour(), distance);
                    pq.add(edge.neighbour());
                }
            }
        }

        return map.get(request.destination());
    }

    @Override
    public String toString() {
        return "Dijkstra";
    }
}
