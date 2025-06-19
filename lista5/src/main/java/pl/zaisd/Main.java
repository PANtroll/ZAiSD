package pl.zaisd;

import pl.zaisd.graph.GraphRequest;
import pl.zaisd.graph.ImportGraph;
import pl.zaisd.graph.Node;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String DELIMITER = ";";

    public static void main(String[] args) throws IOException {
        File file = new File("lista5/in.txt");
        ImportGraph importGraph = new ImportGraph();
        List<Node> graph = new ArrayList<>();
        List<GraphRequest> requests = new ArrayList<>();
        importGraph.importFromFile(file, graph, requests);
        System.out.println(graph);

        Algorithm dijkstra = new Dijkstra();
        Algorithm bellmanFord = new BellmanFord();

        File path = new File("lista5/dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (GraphRequest request : requests) {
            if(!BFS.checkPath(request.source(), request.destination())) {
                System.out.println("Brak ścieżki dla " + request);
            }
            else {
                int bellmanFordResult = runWithStopwatch(bellmanFord, graph, request, writer);
                int dijkstraResult = runWithStopwatch(dijkstra, graph, request, writer);
                if (bellmanFordResult != dijkstraResult) {
                    throw new RuntimeException("Different result for " + request);
                }
            }
        }


        writer.close();
    }

    private static int runWithStopwatch(Algorithm algorithm, List<Node> graph, GraphRequest request, BufferedWriter writer) throws IOException {
        int result = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            result = algorithm.search(graph, request);
        }
        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write( algorithm +  durationStr + DELIMITER + result + "\r\n");
        System.out.println(algorithm + ", duration: " + duration + "ms");
        System.out.println("Result: " + result);
        return result;
    }
}