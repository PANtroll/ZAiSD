package pl.zaisd;

import java.util.List;

public class GreedySequence implements Sequence {
    @Override
    public ResultType getMaxSequence(List<Integer> sequence) {
        ResultType result = null;
        int maxSum = Integer.MIN_VALUE;
        int sum = Integer.MIN_VALUE;
        int startIndex = 0;
        for (int i = 0; i < sequence.size(); i++) {
            Integer current = sequence.get(i);
            if(current > maxSum + current) {
                maxSum = current;
                startIndex = i;
            }
            else {
                maxSum += current;
            }
            if(maxSum > sum) {
                sum = maxSum;
                result = new ResultType(startIndex, i, sum);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "GreedySequence";
    }
}
