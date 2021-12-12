package edu.csf.oop.java;

import java.lang.reflect.Array;
import java.util.List;

public class BStarTree<T extends Comparable<T>> {
    private BStarTreeNode<T> root;
    private final int degree;

    public BStarTree(int degree) throws Exception {
        if (degree < 3) {
            throw new Exception("Degree must be more than 2");
        }
        root = new BStarTreeNode<>(degree, null);
        this.degree = degree;
    }

    public void add(T value) {
        root.add(value);
    }

    public List<T> toList() {
        return root.treeToString();
    }
}
