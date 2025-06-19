package pl.zaisd;

public class HashArrayLine<V> extends AbstractHashArray<V> {

    @Override
    protected int hashConflictIncrease(int hash) {
        return (hash + 1 ) % (capacity() - 1);
    }
}
