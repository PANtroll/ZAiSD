package pl.zaisd;

public class HashArrayDouble<V> extends AbstractHashArray<V> {
    @Override
    protected int hashConflictIncrease(int hash, int attempt) {
        if(hash == 0){
            return 1;
        }
        int newHash = ((hash + (2 * attempt)) % capacity());
        return newHash;
    }
    @Override
    public String toString() {
        return "HashArrayDouble";
    }
}
