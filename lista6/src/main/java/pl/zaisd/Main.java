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


        HashArray<Integer> hashArrayLine = new HashArrayLine<>();
        HashArray<Integer> hashArrayDouble = new HashArrayDouble<>();
        HashArray<Integer> hashArraySquare = new HashArraySquare<>();

        File path = new File("lista6/dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (int i = 10; i < 50_000; i = i*2) {
            runWithStopwatch(hashArrayLine, writer, i);
            runWithStopwatch(hashArrayDouble, writer, i);
            runWithStopwatch(hashArraySquare, writer, i);
        }
        writer.close();
    }

    private static void runWithStopwatch(HashArray hashArray, BufferedWriter writer, int iterations) throws IOException {
        int result = 0;
        Random r = new Random();
        List<Integer> values = new LinkedList<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int value = r.nextInt();
            values.add(value);
            hashArray.insert(value);
        }
        for (Integer value: values){
            Object searchedObject = hashArray.search(value);
            if(searchedObject == null || !value.equals(searchedObject)){
                throw new IllegalStateException("Object not found");
            }
        }
        for (int i = 0; i < iterations; i++) {
            Integer value = r.nextInt();
            Object searchedObject = hashArray.search(value);
            if(values.contains(value) && !value.equals(searchedObject)){
                throw new IllegalStateException("Object not found");
            }
        }

        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write(hashArray + durationStr + DELIMITER + iterations + "\r\n");
        System.out.println(hashArray + ", duration: " + duration + "ms");
//        System.out.println("Result: " + result);
    }
    }