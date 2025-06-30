package pl.zaisd;

import pl.zaisd.graph.Graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    private static final String DELIMITER = ";";

    public static void main(String[] args) throws IOException {

        File path = new File("lista8/dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        Algorithm bruteForce = new BruteForce();
        Algorithm branchAndBound = new BranchAndBound();
        Random r = new Random();
        for (int i = 3; i <= 15; i++) {
            Graph graph = new Graph(i);
            int startPoint = r.nextInt(i);
            runWithStopwatch(bruteForce, graph, startPoint, writer);
            runWithStopwatch(branchAndBound, graph, startPoint, writer);
        }
        writer.close();
    }

    private static int runWithStopwatch(Algorithm algorithm, Graph graph, int startPoint, BufferedWriter writer) throws IOException {
        int result = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            result = algorithm.search(graph, startPoint);
        }
        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write(algorithm + durationStr + DELIMITER + result +  DELIMITER + graph.size() +"\r\n");
        System.out.println(algorithm + ", duration: " + duration + "ms");
        System.out.println("Result: " + result + ", nodes: " + graph.size());
        return result;
    }
}