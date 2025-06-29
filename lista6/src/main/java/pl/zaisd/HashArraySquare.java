package pl.zaisd;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public class HashArraySquare<V> extends AbstractHashArray<V>{

    @Override
    protected int hashConflictIncrease(int hash, int attempt) {
        int newHash = (hash + (attempt * attempt)) % capacity();
        return newHash;
    }

    @Override
    public String toString() {
        return "HashArraySquare";
    }
}
