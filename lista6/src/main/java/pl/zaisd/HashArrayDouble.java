package pl.zaisd;

public class HashArrayDouble<V> extends AbstractHashArray<V> {
    @Override
    protected int hashConflictIncrease(int hash) {
        return (hash * 2) % (capacity() - 1);
    }
}
