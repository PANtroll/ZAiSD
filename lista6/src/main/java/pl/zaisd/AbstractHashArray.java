package pl.zaisd;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractHashArray<V> {

    Object[] array = new Object[7];

    private int size = 0;
    private int capacity = 7;

    public int size() {
        return size;
    }

    public int capacity() {return capacity;}

    public boolean isEmpty() {
        return size == 0;
    }

    protected abstract int hashConflictIncrease(int hash);

    public V search(V value) {
        if (value == null) {
            return null;
        }
        int hash = Math.abs(Objects.hashCode(value)) % (capacity - 1);
        V result = null;
        V obj = (V) array[hash];
        while (!value.equals(obj)) {
            hash = hashConflictIncrease(hash);
            obj = (V) array[(hash) % size];
        }
        if (value.equals(obj)) {
            result = obj;
        }
        return result;
    }

    public V insert(V value) {
        checkCapacityAndResize();
        int hash = Math.abs(Objects.hashCode(value) % (capacity  - 1));
        while (array[hash] != null) {
            hash = hashConflictIncrease(hash);
        }
        array[hash] = value;
        size++;
        return null;
    }

    public V remove(Object value) {
        if (value == null) {
            return null;
        }
        int hash = Math.abs(Objects.hashCode(value));
        V result = null;
        V obj = (V) array[hash];
        while (!value.equals(obj)) {
            hash = hashConflictIncrease(hash);
            obj = (V) array[(hash) % size];
        }
        if (value.equals(obj)) {
            result = obj;
            array[hash] = null;
            size--;
        }
        return result;
    }

    public void clear() {
        array = new Object[capacity];
        size = 0;
    }

    private boolean checkCapacityAndResize() {
        if (size < 0.75 * capacity) {
            return false;
        }
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
