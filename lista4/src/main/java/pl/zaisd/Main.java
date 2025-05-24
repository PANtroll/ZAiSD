package pl.zaisd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static final String DELIMITER = ";";

    public static void main(String[] args) throws IOException {
//        List<Integer> arrayList = Arrays.asList(2,-1,3,-5);

        Sequence naiveSequence = new NaiveSequence();
        Sequence dynamicSequence = new DynamicSequence();
        Sequence divideConquerSequence = new DivideConquerSequence();
        Sequence greedySequence = new GreedySequence();

        File path = new File("lista4/dane.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        for (int i = 10; i < 5_000; i = (int) (i*1.5)) {
            List<Integer> arrayList = generateData(i);
//            System.out.println(arrayList);
            System.out.println("Size: " + arrayList.size());
            runWithStopwatch(naiveSequence, arrayList, writer);
            runWithStopwatch(dynamicSequence, arrayList, writer);
            runWithStopwatch(divideConquerSequence, arrayList, writer);
            runWithStopwatch(greedySequence, arrayList, writer);
        }

        writer.close();
    }

    private static List<Integer> generateData(int numberOfElements) {
        ArrayList<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfElements; i++) {
            int integer = random.nextInt(1_000_000);
            if(i % 2 != 0){
                integer = integer * -1;
            }
            result.add(integer);
        }
        return result;
    }

    private static void runWithStopwatch(Sequence algorithm, List<Integer> sequence, BufferedWriter writer) throws IOException {
        long startTime = System.nanoTime();
        ResultType result = null;
        for (int i = 0; i < 5; i++) {
            result = algorithm.getMaxSequence(sequence);
        }
        double duration = (System.nanoTime() - startTime) / 5_000_000.0;// 5 invokes
        String durationStr = (DELIMITER + duration).replace('.', ',');
        writer.write( algorithm +  durationStr + DELIMITER + sequence.size() + "\r\n");
        System.out.println(algorithm + ", duration: " + duration + "ms");
        System.out.println("Result: " + result);
    }

}