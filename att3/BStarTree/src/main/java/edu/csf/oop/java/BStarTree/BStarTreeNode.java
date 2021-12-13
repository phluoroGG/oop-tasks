package edu.csf.oop.java.BStarTree;

import java.util.ArrayList;
import java.util.List;

class BStarTreeNode<T extends Comparable<T>> {
    private List<T> keys;
    private final int degree;
    private List<BStarTreeNode<T>> children;
    private BStarTreeNode<T> parent;
    private boolean isLeaf;

    public BStarTreeNode(int degree, BStarTreeNode<T> parent) {
        this.degree = degree;
        keys = new ArrayList<>();
        children = new ArrayList<>();
        this.parent = parent;
        isLeaf = true;
    }

    public List<T> getKeys() {
        return keys;
    }

    public int getDegree() {
        return degree;
    }

    public List<BStarTreeNode<T>> getChildren() {
        return children;
    }

    public BStarTreeNode<T> getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setKeys(List<T> keys) {
        this.keys = keys;
    }

    public void setChildren(List<BStarTreeNode<T>> children) {
        this.children = children;
    }

    public void setParent(BStarTreeNode<T> parent) {
        this.parent = parent;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
