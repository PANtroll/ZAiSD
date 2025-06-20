package pl.zaisd;

import java.util.Objects;

public class HashArrayDouble<V> extends AbstractHashArray<V> {
    @Override
    protected int hashConflictIncrease(int hash, int attempt) {
        int hash2 = 1 + (Objects.hashCode(hash) % (capacity() - 1));
        int newHash = ((hash + (attempt * hash2)) % capacity());
        return newHash;
    }
    @Override
    public String toString() {
        return "HashArrayDouble";
    }
}
