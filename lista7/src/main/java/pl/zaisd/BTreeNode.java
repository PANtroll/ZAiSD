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

    public T search(T key) {
        int i = 0;
        while (i < keys.size() && key.compareTo(keys.get(i)) > 0) {
            i++;
        }
        if (i < keys.size() && key.compareTo(keys.get(i)) == 0) {
            return keys.get(i);
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



    void remove(T key) {
        int idx = findKey(key);

        if (idx < keys.size() && keys.get(idx).compareTo(key) == 0) {
            if (leaf) removeFromLeaf(idx);
            else removeFromNonLeaf(idx);
        } else {
            if (leaf) return; // Key not found

            boolean flag = (idx == keys.size());
            if (children.get(idx).keys.size() < t) fill(idx);

            if (flag && idx > keys.size()) children.get(idx - 1).remove(key);
            else children.get(idx).remove(key);
        }
    }

    int findKey(T key) {
        int idx = 0;
        while (idx < keys.size() && keys.get(idx).compareTo(key) < 0) ++idx;
        return idx;
    }

    void removeFromLeaf(int idx) {
        keys.remove(idx);
    }

    void removeFromNonLeaf(int idx) {
        T k = keys.get(idx);

        if (children.get(idx).keys.size() >= t) {
            T pred = getPredecessor(idx);
            keys.set(idx, pred);
            children.get(idx).remove(pred);
        } else if (children.get(idx + 1).keys.size() >= t) {
            T succ = getSuccessor(idx);
            keys.set(idx, succ);
            children.get(idx + 1).remove(succ);
        } else {
            merge(idx);
            children.get(idx).remove(k);
        }
    }

    T getPredecessor(int idx) {
        BTreeNode<T> cur = children.get(idx);
        while (!cur.leaf) cur = cur.children.get(cur.children.size() - 1);
        return cur.keys.get(cur.keys.size() - 1);
    }

    T getSuccessor(int idx) {
        BTreeNode<T> cur = children.get(idx + 1);
        while (!cur.leaf) cur = cur.children.get(0);
        return cur.keys.get(0);
    }

    void fill(int idx) {
        if (idx != 0 && children.get(idx - 1).keys.size() >= t)
            borrowFromPrev(idx);
        else if (idx != keys.size() && children.get(idx + 1).keys.size() >= t)
            borrowFromNext(idx);
        else {
            if (idx != keys.size()) merge(idx);
            else merge(idx - 1);
        }
    }

    void borrowFromPrev(int idx) {
        BTreeNode<T> child = children.get(idx);
        BTreeNode<T> sibling = children.get(idx - 1);

        child.keys.add(0, keys.get(idx - 1));
        if (!child.leaf)
            child.children.add(0, sibling.children.remove(sibling.children.size() - 1));

        keys.set(idx - 1, sibling.keys.remove(sibling.keys.size() - 1));
    }

    void borrowFromNext(int idx) {
        BTreeNode<T> child = children.get(idx);
        BTreeNode<T> sibling = children.get(idx + 1);

        child.keys.add(keys.get(idx));
        if (!child.leaf)
            child.children.add(sibling.children.remove(0));

        keys.set(idx, sibling.keys.remove(0));
    }

    void merge(int idx) {
        BTreeNode<T> child = children.get(idx);
        BTreeNode<T> sibling = children.get(idx + 1);

        child.keys.add(keys.remove(idx));
        child.keys.addAll(sibling.keys);

        if (!child.leaf) child.children.addAll(sibling.children);

        children.remove(idx + 1);
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