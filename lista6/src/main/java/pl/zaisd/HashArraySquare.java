package pl.zaisd;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public class HashArraySquare<V> extends AbstractHashArray<V>{

    @Override
    protected int hashConflictIncrease(int hash) {
        return (hash * hash) % (capacity() - 1);
    }
}
