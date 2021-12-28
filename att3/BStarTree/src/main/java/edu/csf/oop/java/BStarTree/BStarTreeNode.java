package edu.csf.oop.java.BStarTree;

import java.util.ArrayList;
import java.util.List;

public class BStarTreeNode<T extends Comparable<T>> {
    private final List<T> keys;
    private final List<BStarTreeNode<T>> children;
    private BStarTreeNode<T> parent;

    public BStarTreeNode(BStarTreeNode<T> parent) {
        this.parent = parent;
        keys = new ArrayList<>();
        children = new ArrayList<>();
    }

    List<T> getKeys() {
        return keys;
    }

    public List<T> getKeysReadOnly() {
        return new ArrayList<>(keys);
    }

    List<BStarTreeNode<T>> getChildren() {
        return children;
    }

    public List<BStarTreeNode<T>> getChildrenReadOnly() {
        return new ArrayList<>(children);
    }

    public BStarTreeNode<T> getParent() {
        return parent;
    }

    void setParent(BStarTreeNode<T> parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    int countOfKeys() {
        int count = keys.size();
        for (BStarTreeNode<T> node : children) {
            count += node.keys.size();
        }
        return count;
    }
}
