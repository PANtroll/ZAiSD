package pl.zaisd;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GraphGenerator {

    public static void main(String[] args) {
        Path path = Path.of("lista5/in.txt");
        GraphGenerator instance = new GraphGenerator();
        instance.generateFile(path);
    }

    public void generateFile(Path path){
        Random random = new Random();
//        int numberOfNodes = random.nextInt(2, 1000);
//        int numberOfEdges = random.nextInt(1, 100_000);
//        int numberOfRequests = random.nextInt(1, 10_000);
        int numberOfNodes = 1000;
        int numberOfEdges = 10_000;
        int numberOfRequests = 500;
        List<Connection> edges = generateGraphConnections(numberOfNodes, numberOfEdges);
        List<Connection> requests = generateGraphConnections(numberOfNodes, numberOfRequests);

        //write
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            String meta = numberOfNodes + " " + edges.size();
            writer.write(meta);
            writer.newLine();
            for(Connection connection : edges){
                String line = connection + " " + random.nextInt(1, 10_000);
                writer.write(line);
                writer.newLine();
            }
            writer.write((String.valueOf(requests.size())));
            writer.newLine();
            for (Connection connection : requests){
                writer.write(connection.toString());
                writer.newLine();
            }
        }
        catch (IOException e){
            System.out.println("Cannot write to file" + e);
        }

    }

    private List<Connection> generateGraphConnections(int numberOfNodes, int loopLimit) {
        int skips;
        List<Connection> result = new ArrayList<>();
        Random random = new Random();
        skips = 0;
        for (int i = 0; i < loopLimit + skips; i++) {
            int node1 = random.nextInt(0, numberOfNodes - 1);
            int node2 = random.nextInt(0, numberOfNodes - 1);
            if(node1 == node2){
                skips++;
                continue;
            }
            Connection connection = new Connection(node1, node2);
            if(result.contains(connection)) {
                skips++;
                continue;
            }
            result.add(connection);

            if(skips > loopLimit){
                break;
            }
        }
        return result;
    }

    private record Connection(int node1, int node2) {
        @Override
        public String toString() {
            return node1 + " " + node2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Connection that = (Connection) o;
            return (node1 == that.node1 && node2 == that.node2) ||
                    (node1 == that.node2 && node2 == that.node1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(node1, node2);
        }
    }
}
