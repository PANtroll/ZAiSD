package pl.zaisd;

public class HashArrayLine<V> extends AbstractHashArray<V> {

    @Override
    protected int hashConflictIncrease(int hash, int attempt) {
        int newHash = (hash + attempt) % capacity();
        return newHash;
    }

    @Override
    public String toString() {
        return "HashArrayLine";
    }
}
