package pl.zaisd;


import java.util.ArrayList;
import java.util.List;

public class BTreeNode<T extends Comparable<T>> {
    int t;
    List<T> keys;
    List<BTreeNode<T>> children;
    boolean leaf;

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public BTreeNode<T> search(T key) {
        int i = 0;
        while (i < keys.size() && key.compareTo(keys.get(i)) > 0) {
            i++;
        }
        if (i < keys.size() && key.compareTo(keys.get(i)) == 0) {
            return this;
        }
        if (leaf) return null;
        return children.get(i).search(key);
    }

    public void insertNonFull(T key) {
        int i = keys.size() - 1;
        if (leaf) {
            keys.add(null);
            while (i >= 0 && key.compareTo(keys.get(i)) < 0) {
                keys.set(i + 1, keys.get(i));
                i--;
            }
            keys.set(i + 1, key);
        } else {
            while (i >= 0 && key.compareTo(keys.get(i)) < 0) {
                i--;
            }
            i++;
            if (children.get(i).keys.size() == 2 * t - 1) {
                splitChild(i);
                if (key.compareTo(keys.get(i)) > 0) {
                    i++;
                }
            }
            children.get(i).insertNonFull(key);
        }
    }

    public void splitChild(int i) {
        BTreeNode<T> y = children.get(i);
        BTreeNode<T> z = new BTreeNode<>(y.t, y.leaf);
        for (int j = 0; j < t - 1; j++) {
            z.keys.add(y.keys.remove(t));
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.remove(t));
            }
        }
        children.add(i + 1, z);
        keys.add(i, y.keys.remove(t - 1));
    }

    public void traverse() {
        int i;
        for (i = 0; i < keys.size(); i++) {
            if (!leaf) children.get(i).traverse();
            System.out.print(keys.get(i) + " ");
        }
        if (!leaf) children.get(i).traverse();
    }
}