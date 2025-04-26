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
        List<Integer> arrayList = new ArrayList<>();
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();

        File path = new File("dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        int max = 100_000;
        for (int i = 100; i < max; i += 300) {
            runWithStopwatch(i, dynamicArray, writer);
            runWithStopwatch(i, arrayList, writer);
            System.out.println("progress: " + i * 100 / max);
        }
        writer.close();
    }

    private static void runWithStopwatch(int max, DynamicArray<Integer> dynamicArray, BufferedWriter writer) throws IOException {
        long startTime = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            doSomeOperationOnArray(max, dynamicArray);
        }
        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write("DynamicArray" + DELIMITER + max + durationStr + DELIMITER + "\r\n");
        System.out.println("Duration: " + duration + "ms");
    }

    private static void runWithStopwatch(int max, List<Integer> arrayList, BufferedWriter writer) throws IOException {
        long startTime = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            doSomeOperationOnList(max, arrayList);
        }
        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write("ArrayList" + DELIMITER + max + durationStr + DELIMITER + "\r\n");
        System.out.println("Duration: " + duration + "ms");
    }

    private static void doSomeOperationOnArray(int max, DynamicArray<Integer> dynamicArray) throws IOException {
        for (int i = 0; i < max; i++) {
            dynamicArray.append(i);
            dynamicArray.append(i);
            dynamicArray.cut();
//            System.out.println(dynamicArray.toFullArrayString());
//            System.out.println(dynamicArray);
//            System.out.println();
        }
        for (int i = 0; i < max; i++) {
            dynamicArray.cut();
            dynamicArray.append(i);
            dynamicArray.append(i);
            dynamicArray.cut();
            dynamicArray.append(i);
            dynamicArray.cut();
            dynamicArray.append(i);
            dynamicArray.append(i);
            dynamicArray.cut();
            dynamicArray.cut();
//            System.out.println(dynamicArray.toFullArrayString());
//            System.out.println(dynamicArray);
//            System.out.println();
        }
        Random r = new Random();
        for (int i = 0; i < max; i++) {
            dynamicArray.at(r.nextInt(dynamicArray.size()));
            dynamicArray.at(r.nextInt(dynamicArray.size()));
            dynamicArray.at(r.nextInt(dynamicArray.size()));
            dynamicArray.at(r.nextInt(dynamicArray.size()));
        }
        for (int i = max; i > 0; i--) {
            dynamicArray.append(i);
            dynamicArray.cut();
            dynamicArray.cut();
//            System.out.println(dynamicArray.toFullArrayString());
//            System.out.println(dynamicArray);
//            System.out.println();
        }
    }

    private static void doSomeOperationOnList(int max, List<Integer> arrayList) throws IOException {
        for (int i = 0; i < max; i++) {
            arrayList.add(i);
            arrayList.add(i);
            arrayList.remove(arrayList.size() - 1);
//            System.out.println(arrayList);
//            System.out.println();
        }
        for (int i = 0; i < max; i++) {
            arrayList.remove(arrayList.size() - 1);
            arrayList.add(i);
            arrayList.add(i);
            arrayList.remove(arrayList.size() - 1);
            arrayList.add(i);
            arrayList.remove(arrayList.size() - 1);
            arrayList.add(i);
            arrayList.add(i);
            arrayList.remove(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);
//            System.out.println(arrayList);
//            System.out.println();
        }
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            arrayList.get(r.nextInt(arrayList.size()));
        }
        for (int i = max; i > 0; i--) {
            arrayList.add(i);
            arrayList.remove(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);
//            System.out.println(arrayList);
//            System.out.println();
        }
    }

}