package pl.zaisd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final String DELIMITER = ";";

    public static void main(String[] args) throws IOException {
        List<Integer> arrayList = generateData(10);
        File path = new File("dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        System.out.println(arrayList);
        int max = 100_000;
        for (int i = 100; i < max; i += 300) {
            runWithStopwatch(i, arrayList, writer);
            System.out.println("progress: " + i * 100 / max);
        }
        writer.close();
    }

    private static List<Integer> generateData(int numberOfElements) {
        ArrayList<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfElements; i++) {
            int integer = random.nextInt(Integer.MAX_VALUE);
            if(i % 2 != 0){
                integer = integer * -1;
            }
            result.add(integer);
        }
        return result;
    }

    private static void runWithStopwatch(int max, List<Integer> dynamicArray, BufferedWriter writer) throws IOException {
        long startTime = System.nanoTime();
        for (int i = 0; i < 5; i++) {
//            doSomeOperationOnArray(max, dynamicArray);
        }
        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write("DynamicArray" + DELIMITER + max + durationStr + DELIMITER + "\r\n");
        System.out.println("Duration: " + duration + "ms");
    }

}