package pl.zaisd;

import pl.zaisd.graph.Edge;
import pl.zaisd.graph.Node;

import java.util.*;

public class BFS {

    public static boolean checkPath(Node source, Node destination){
        List<Node> checkedNodes = new LinkedList<>();
        Queue<Node> nodesToCheck = new LinkedList<>();
        nodesToCheck.add(source);
        while (!nodesToCheck.isEmpty()){
            for (Edge edge: nodesToCheck.poll().neighbours()){

                Node node = edge.neighbour();
                if(checkedNodes.contains(node)){
                    continue;
                }
                if(node.equals(destination)){
                    return true;
                }
                nodesToCheck.add(node);
                checkedNodes.add(node);
            }
        }

        return false;
    }

}
