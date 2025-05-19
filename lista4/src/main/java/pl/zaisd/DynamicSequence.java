package pl.zaisd;

import java.util.List;

public class DynamicSequence implements Sequence {
    @Override
    public ResultType getMaxSequence(List<Integer> sequence) {
        ResultType result = null;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < sequence.size(); i++) {
            int sum = 0;
            for (int j = i; j < sequence.size(); j++) {
                sum += sequence.get(j);
                if(sum > maxSum) {
                    maxSum = sum;
                    result = new ResultType(i, j, maxSum);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "DynamicSequence";
    }
}
