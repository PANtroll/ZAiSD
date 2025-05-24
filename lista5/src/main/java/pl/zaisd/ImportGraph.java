package pl.zaisd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ImportGraph {

    public static final String SPACE = " ";

    public void importFromFile(File file, List<Node> graph, List<GraphRequest> requests) {
        BufferedReader sb = null;
        try {
            sb = Files.newBufferedReader(file.toPath());
            int numberOfNodes = 0;
            int numberOfEdges = 0;
            String line = sb.readLine();
            if (line.isBlank()) return;

            String[] split = line.split(SPACE);
            numberOfNodes = Integer.parseInt(split[0]);
            numberOfEdges = Integer.parseInt(split[1]);

            for (int i = 0; i < numberOfNodes; i++) {
                graph.add(new Node(new ArrayList<>()));
            }
            for (int i = 0; i < numberOfEdges; i++) {
                line = sb.readLine();
                if (line.isBlank()) return;
                split = line.split(SPACE);
                int node1Index = Integer.parseInt(split[0]);
                int node2Index = Integer.parseInt(split[1]);
                int wight = Integer.parseInt(split[2]);
                Node node1 = graph.get(node1Index);
                Node node2 = graph.get(node2Index);
                node1.addEdge(node2, wight);
                node2.addEdge(node1, wight);
            }
            line = sb.readLine();
            int numberOfRequests = 0;
            if (line.isBlank()) return;

            split = line.split(SPACE);
            numberOfRequests = Integer.parseInt(split[0]);

            for (int i = 0; i < numberOfRequests; i++) {
                line = sb.readLine();
                if (line.isBlank()) return;
                split = line.split(SPACE);
                int node1Index = Integer.parseInt(split[0]);
                int node2Index = Integer.parseInt(split[1]);
                Node node1 = graph.get(node1Index);
                Node node2 = graph.get(node2Index);
                requests.add(new GraphRequest(node1, node2));
            }

        } catch (IOException e) {
            System.out.println("Error occur");
        }
    }
}
