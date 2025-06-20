package pl.zaisd;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractHashArray<V> implements HashArray<V> {

    public static final int INITIAL_ARRAY_SIZE = 11;
    Object[] array = new Object[INITIAL_ARRAY_SIZE];

    private int size = 0;
    private int capacity = INITIAL_ARRAY_SIZE;

    public int size() {
        return size;
    }

    public int capacity() {return capacity;}

    public boolean isEmpty() {
        return size == 0;
    }

    protected abstract int hashConflictIncrease(int hash, int attempt);

    public V search(V value) {
        if (value == null) {
            return null;
        }
        int hash = getHash(value);
        int subHash = hash;
        V obj = (V) array[hash];
        int searchAttempts = 0;
        while (!value.equals(obj) && obj != null) {
            if (searchAttempts > 2 * Math.log(capacity) + 1) {
//                hash++;
//                searchAttempts = 0;
                System.out.println(this.toString() + ": Too many searches: " + searchAttempts);
            }
            subHash = hashConflictIncrease(hash, searchAttempts);
            obj = (V) array[subHash];
            searchAttempts++;
        }
//        System.out.println("value found: " + value + " index: " + hash + " searchAttempts: " + searchAttempts);
        return obj;
    }

    public V insert(V value) {
        checkCapacityAndResize();
        int hash = getHash(value);
        int subHash = hash;
        int insertAttempts = 0;
        while (array[subHash] != null) {
            if (insertAttempts > 2 * Math.log(capacity) + 1) {
//                hash++;
//                insertAttempts = 0;
                System.out.println(this.toString() + ": Too many inserts: " + insertAttempts);
            }
            subHash = hashConflictIncrease(hash, insertAttempts);
            insertAttempts++;
        }
        hash = subHash;
//        System.out.println("value added: " + value + " index: " + hash + " insertAttempts: " + insertAttempts);
        array[hash] = value;
        size++;
        return null;
    }

    public V remove(V value) {
        if (value == null) {
            return null;
        }
        int hash = getHash(value);
        V result = null;
        V obj = (V) array[hash];
        int removeAttempts = 0;
        while (!value.equals(obj)) {
            hash = hashConflictIncrease(hash, removeAttempts);
            obj = (V) array[(hash) % size];
            removeAttempts++;
        }
        if (value.equals(obj)) {
            result = obj;
            array[hash] = null;
            size--;
        }
        return result;
    }

    private int getHash(V value) {
        return Math.abs(Objects.hashCode(value) % capacity);
    }

    public void clear() {
        array = new Object[capacity];
        size = 0;
    }

    private boolean checkCapacityAndResize() {
        if (size + 1  < 0.66 * capacity) {
            return false;
        }
//        System.out.println("increased capacity");
        Object[] oldArray = Arrays.copyOf(array, capacity);
        int newCapacity = 2 * capacity + 1;
        while (!new BigInteger(String.valueOf(newCapacity)).isProbablePrime(7)) {
            newCapacity++;
        }
        capacity = newCapacity;
        array = new Object[newCapacity];
        size = 0;
        int objectToRehash = size;
        for (int i = 0; i < oldArray.length; i++) {
            Object value = oldArray[i];
            if (value != null) {
                insert((V) value);
                objectToRehash--;
                if (objectToRehash == 0) {
                    return true;
                }
            }
        }
        return true;
    }
}
