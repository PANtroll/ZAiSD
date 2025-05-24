package pl.zaisd;

import pl.zaisd.graph.GraphRequest;
import pl.zaisd.graph.Node;

import java.util.List;

public interface Algorithm {

    int search(List<Node> graph, GraphRequest request);
}
