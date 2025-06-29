package pl.zaisd;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final String DELIMITER = ";";
    public static final int NUMBER_OF_INSERTIONS = 50;

    public static void main(String[] args) throws IOException {


        HashArray<Double> hashArrayLine = new HashArrayLine<>();
        HashArray<Double> hashArrayDouble = new HashArrayDouble<>();
        HashArray<Double> hashArraySquare = new HashArraySquare<>();
        Set<Double> set = new HashSet<>();

        File path = new File("lista6/dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (int i = 10; i < 100_000; i = i * 2) {
            runWithStopwatch(hashArrayLine, writer, i);
            runWithStopwatch(hashArrayDouble, writer, i);
            runWithStopwatch(hashArraySquare, writer, i);
            runWithStopwatch(set, writer, i);
        }
        writer.close();
    }

    private static void runWithStopwatch(HashArray hashArray, BufferedWriter writer, int iterations) throws IOException {
        int result = 0;
        Random r = new Random();
        List<Double> values = new LinkedList<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            Double value = r.nextDouble();
            values.add(value);
            hashArray.insert(value);
        }
        for (Double value : values) {
            Object searchedObject = hashArray.search(value);
            if (searchedObject == null || !value.equals(searchedObject)) {
                throw new IllegalStateException("Object not found");
            }
        }
        for (int i = 0; i < iterations; i++) {
            Double value = r.nextDouble();
            Object searchedObject = hashArray.search(value);
            if (values.contains(value) && !value.equals(searchedObject)) {
                throw new IllegalStateException("Object not found");
            }
        }

        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write(hashArray + durationStr + DELIMITER + iterations + "\r\n");
        System.out.println(hashArray + ", duration: " + duration + "ms");
//        System.out.println("Result: " + result);
    }
    private static void runWithStopwatch(Set set, BufferedWriter writer, int iterations) throws IOException {
        int result = 0;
        Random r = new Random();
        List<Double> values = new LinkedList<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            double value = r.nextDouble();
            values.add(value);
            set.add(value);
        }
        for (Double value : values) {
            boolean searchedObject = set.contains(value);
            if (!searchedObject) {
                throw new IllegalStateException("Object not found");
            }
        }
        for (int i = 0; i < iterations; i++) {
            Double value = r.nextDouble();
            boolean searchedObject = set.contains(value);
            if (values.contains(value) && !searchedObject) {
                throw new IllegalStateException("Object not found");
            }
        }

        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write("HashSet" + durationStr + DELIMITER + iterations + "\r\n");
        System.out.println("HashSet" + ", duration: " + duration + "ms");
//        System.out.println("Result: " + result);
    }

    private static String randomString(Random random, int maxLength){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        return random.ints(leftLimit, rightLimit + 1)
                .limit(maxLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}