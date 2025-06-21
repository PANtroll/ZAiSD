package pl.zaisd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String DELIMITER = ";";

    public static void main(String[] args) throws IOException {

        File path = new File("lista6/dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (int i = 10; i < 100_000; i = i * 2) {
            int t  = 2;
            BTree<Integer> integerBTree = new BTree<>(t);
            BTree<Double> doubleBTree = new BTree<>(t);
            BTree<String> stringBTree = new BTree<>(t);
            runWithStopwatch(integerBTree, writer, i);
            runWithStopwatch(doubleBTree, writer, i);
            runWithStopwatch(stringBTree, writer, i);
        }
        writer.close();
    }

    private static void runWithStopwatch(BTree tree, BufferedWriter writer, int iterations) throws IOException {
        int result = 0;
        Random r = new Random();
        List<Integer> values = new LinkedList<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int value = r.nextInt();
            values.add(value);
            tree.insert(value);
        }
        for (Integer value : values) {
            Object searchedObject = tree.search(value);
            if (searchedObject == null || !value.equals(searchedObject)) {
                throw new IllegalStateException("Object not found");
            }
        }
        for (int i = 0; i < iterations; i++) {
            Integer value = r.nextInt();
            Object searchedObject = tree.search(value);
            if (values.contains(value) && !value.equals(searchedObject)) {
                throw new IllegalStateException("Object not found");
            }
        }

        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write(tree + durationStr + DELIMITER + iterations + "\r\n");
        System.out.println(tree + ", duration: " + duration + "ms");
//        System.out.println("Result: " + result);
    }
}