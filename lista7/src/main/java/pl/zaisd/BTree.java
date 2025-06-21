package pl.zaisd;
public class BTree<T extends Comparable<T>> {
    private BTreeNode<T> root;
    private int t;

    BTree(int t) {
        this.root = null;
        this.t = t;
    }

    void traverse() {
        if (root != null) root.traverse();
    }

    public BTreeNode search(T key) {
        return (root == null) ? null : root.search(key);
    }

    public void insert(T key) {
        if (root == null) {
            root = new BTreeNode<>(t, true);
            root.keys.add(key);
        } else {
            if (search(key) != null) return; // no duplicates
            if (root.keys.size() == 2 * t - 1) {
                BTreeNode<T> s = new BTreeNode<>(t, false);
                s.children.add(root);
                s.splitChild(0);
                int i = (s.keys.get(0).compareTo(key) < 0) ? 1 : 0;
                s.children.get(i).insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }
}
