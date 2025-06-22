package pl.zaisd;

import com.sun.source.tree.LambdaExpressionTree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final String DELIMITER = ";";
    private static final int ITERATIONS = 100;

    public static void main(String[] args) throws IOException {

        File path = new File("lista7/dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        List<Integer> ts = List.of(2, 16, 128);
        for (int t : ts) {
            BTree<Integer> integerBTree = new BTree<>(t);
            BTree<Double> doubleBTree = new BTree<>(t);
            BTree<String> stringBTree = new BTree<>(t);
            runWithStopwatchInteger(integerBTree, writer);
            runWithStopwatchDouble(doubleBTree, writer);
            runWithStopwatchString(stringBTree, writer);
        }

        writer.close();
    }

    private static void runWithStopwatchInteger(BTree<Integer> tree, BufferedWriter writer) throws IOException {
        Random r = new Random();
        List<Integer> values = new LinkedList<>();
        int ppb = r.nextInt(100);
        long startTime = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            if (ppb <= 51) {
                Integer value = r.nextInt();
                values.add(value);
                tree.insert(value);
            } else if (ppb < 99) {
                if(values.isEmpty()) {
                    continue;
                }
                int value = r.nextInt(values.size());
                Integer searchedObject = values.get(value);
                tree.remove(searchedObject);
                values.remove(searchedObject);
            } else {
                tree.traverse();
            }
            ppb = r.nextInt(100);
        }
        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write(tree + "Integer, " + durationStr + "\r\n");
        System.out.println(tree + "Integer, duration: " + duration + "ms");
    }

    private static void runWithStopwatchDouble(BTree<Double> tree, BufferedWriter writer) throws IOException {
        Random r = new Random();
        List<Double> values = new LinkedList<>();
        int ppb = r.nextInt(100);
        long startTime = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            if (ppb <= 51) {
                Double value = r.nextDouble();
                values.add(value);
                tree.insert(value);
            } else if (ppb < 99) {
                if(values.isEmpty()) {
                    continue;
                }
                int value = r.nextInt(values.size());
                Double searchedObject = values.get(value);
                tree.remove(searchedObject);
                values.remove(searchedObject);
            } else {
                tree.traverse();
            }
            ppb = r.nextInt(100);
        }
        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write(tree + "Double, " + durationStr + "\r\n");
        System.out.println(tree + "Double, duration: " + duration + "ms");
//        System.out.println("Result: " + result);
    }

    private static void runWithStopwatchString(BTree<String> tree, BufferedWriter writer) throws IOException {
        int result = 0;
        Random r = new Random();
        List<String> values = new LinkedList<>();
        int ppb = r.nextInt(100);
        long startTime = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            if (ppb <= 51) {
                String value = randomString(r, 10);
                values.add(value);
                tree.insert(value);
            } else if (ppb < 99) {
                if(values.isEmpty()) {
                    continue;
                }
                int value = r.nextInt(values.size());
                String searchedObject = values.get(value);
                tree.remove(searchedObject);
                values.remove(searchedObject);
            } else {
                tree.traverse();
            }
            ppb = r.nextInt(100);
        }
        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write(tree + "String, " + durationStr + "\r\n");
        System.out.println(tree + "String, duration: " + duration + "ms");
//        System.out.println("Result: " + result);
    }

    private static String randomString(Random random, int maxLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        return random.ints(leftLimit, rightLimit + 1)
                .limit(maxLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}