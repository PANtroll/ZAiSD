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

        for (GraphRequest request: requests){
            if(!BFS.chceckPath(request.source(), request.destination())) {
                System.out.println("Brak ścieżki dla " + request);
                return;
            }
        }

        File path = new File("lista5/dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        runWithStopwatch(dijkstra, graph, requests, writer);

        writer.close();
    }

    private static void runWithStopwatch(Algorithm algorithm, List<Node> graph, List<GraphRequest> requests, BufferedWriter writer) throws IOException {
        long startTime = System.nanoTime();
        int result = 0;
        for (GraphRequest request : requests) {
            for (int i = 0; i < 5; i++) {
                result = algorithm.search(graph, request);
            }
            double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
            String durationStr = (DELIMITER + duration).replace('.', ',');
            writer.write( algorithm +  durationStr + DELIMITER + result + "\r\n");
            System.out.println(algorithm + ", duration: " + duration + "ms");
            System.out.println("Result: " + result);
        }
    }
}