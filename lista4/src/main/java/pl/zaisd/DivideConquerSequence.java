package pl.zaisd;

import java.util.List;

public class DivideConquerSequence implements Sequence {
    @Override
    public ResultType getMaxSequence(List<Integer> sequence) {
        return divideConquer(sequence, 0, sequence.size() - 1);
    }

    private ResultType divideConquer(List<Integer> sequence, int left, int right) {
        if (left == right) {
            return new ResultType(left, right, sequence.get(left));
        }
        int mid = (left + right) / 2;

        ResultType leftResult = divideConquer(sequence, left, mid);
        ResultType rightResult = divideConquer(sequence, mid + 1, right);
        ResultType middleResult = middleSum(sequence, left, mid, right);

        if (leftResult.sum() > rightResult.sum() && leftResult.sum() > middleResult.sum()) {
            return leftResult;
        }
        else if (rightResult.sum() > leftResult.sum() && rightResult.sum() > middleResult.sum()) {
            return rightResult;
        }
        return middleResult;
    }

    private ResultType middleSum(List<Integer> sequence, int left, int mid, int right) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        int maxLeft = mid;
        for (int i = mid; i >= left; i--) {
            sum += sequence.get(i);
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        int maxRight = mid +1;
        for (int i = maxRight; i <= right; i++) {
            sum += sequence.get(i);
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        return new ResultType(maxLeft, maxRight, leftSum + rightSum);
    }

    @Override
    public String toString() {
        return "DivideConquerSequence";
    }
}
