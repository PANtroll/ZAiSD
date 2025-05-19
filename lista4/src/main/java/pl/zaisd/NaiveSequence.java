package pl.zaisd;

import java.util.List;

public class NaiveSequence implements Sequence {

    @Override
    public ResultType getMaxSequence(List<Integer> sequence) {
        ResultType result = null;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < sequence.size(); i++) {
            for (int j = i; j < sequence.size(); j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += sequence.get(k);
                }
                if (sum > maxSum) {
                    maxSum = sum;
                    result = new ResultType(i, j, sum);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "NaiveSequence";
    }
}
