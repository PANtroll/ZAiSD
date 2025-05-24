package pl.zaisd;

import java.util.List;

public class GreedySequence implements Sequence {
    @Override
    public ResultType getMaxSequence(List<Integer> sequence) {
        ResultType result = null;
        int sum = Integer.MIN_VALUE;
        int MaxSum = Integer.MIN_VALUE;
        int startIndex = 0;
        for (int i = 0; i < sequence.size(); i++) {
            Integer current = sequence.get(i);
            if(current > sum + current) {
                sum = current;
                startIndex = i;
            }
            else {
                sum += current;
            }
            if(sum > MaxSum) {
                MaxSum = sum;
                result = new ResultType(startIndex, i, MaxSum);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "GreedySequence";
    }
}
