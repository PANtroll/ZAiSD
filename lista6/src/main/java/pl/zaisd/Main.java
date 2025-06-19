package pl.zaisd;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final String DELIMITER = ";";

    public static void main(String[] args) throws IOException {


        AbstractHashArray<Integer> hashArrayLine = new HashArrayLine<>();
        AbstractHashArray<Integer> hashArrayDouble = new HashArrayDouble<>();
        AbstractHashArray<Integer> hashArraySquare = new HashArraySquare<>();

        File path = new File("lista6/dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        runWithStopwatch(hashArrayLine, writer);
        runWithStopwatch(hashArrayDouble, writer);
        runWithStopwatch(hashArraySquare, writer);

        writer.close();
    }

    private static void runWithStopwatch(AbstractHashArray hashArray, BufferedWriter writer) throws IOException {
        int result = 0;
        Random r = new Random();
        List<Integer> values = new LinkedList<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            int value = r.nextInt();
            values.add(value);
            hashArray.insert(value);
        }
        for (Integer value: values){
            Object searchedObject = hashArray.search(value);
            if(searchedObject == null || value == searchedObject){
                throw new RuntimeException("Object not found");
            }
        }

        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write(hashArray + durationStr + DELIMITER + result + "\r\n");
        System.out.println(hashArray + ", duration: " + duration + "ms");
        System.out.println("Result: " + result);
    }
    }