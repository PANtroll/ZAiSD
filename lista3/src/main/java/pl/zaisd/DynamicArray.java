package pl.zaisd;

import java.util.Arrays;

public class DynamicArray<T> {

    public static final int MIN_SIZE = 4;
    T[] array;

    int size;

    public DynamicArray() {
        this.array = (T[]) new Object[MIN_SIZE];
        this.size = 0;
    }

    public DynamicArray(int size) {
        this.array = (T[]) new Object[size];
        this.size = 0;
    }

    public T at(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void append(T newElement) {
        if (size == array.length) {
            extendArray();
        }
        array[size] = newElement;
        size++;
    }

    public T cut() {
        if (size == 0) {
            return null;
        }
        size--;
        T result = array[size];
        array[size] = null;
        if (size <= array.length / 4) {
            downgradeArray();
        }
        return result;
    }
    public int size() {
        return size;
    }

    private void extendArray() {
        T[] newArray = (T[]) new Object[size * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void downgradeArray() {
        if(array.length <= MIN_SIZE) {
            return;
        }
        T[] newArray = (T[]) new Object[array.length / 2];
        System.arraycopy(array, 0, newArray, 0, array.length / 4);
        array = newArray;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("DynamicArray{" + "array=[");
        result.append("]").append(", capacity=").append(size);
        result.append(", length=").append(array.length).append('}');
        return result.toString();
    }

    public String toFullArrayString() {
        return "DynamicArray{array=" + Arrays.toString(array) + ", capacity=" + size + ", length=" + array.length + '}';
    }

    public String toCSV(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(array[i]);
            if (i != size - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }
}
