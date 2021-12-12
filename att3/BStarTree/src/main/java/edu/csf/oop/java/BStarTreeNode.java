package edu.csf.oop.java;

import java.util.ArrayList;
import java.util.Comparator;
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

    public void add(T value) {
        if (isLeaf) {
            if (keys.size() < degree - 1) {
                keys.add(value);
                keys.sort(Comparator.naturalOrder());
            } else if (parent == null) {
                int middle = (int) Math.ceil((double) degree / 2) - 1;
                keys.add(value);
                keys.sort(Comparator.naturalOrder());
                children.add(new BStarTreeNode<>(degree, this));
                children.add(new BStarTreeNode<>(degree, this));
                for (int i = 0; i < middle; i++) {
                    children.get(children.size() - 2).keys.add(keys.get(0));
                    keys.remove(0);
                }
                int size = keys.size();
                for (int i = 0; i < size - 1; i++) {
                    children.get(children.size() - 1).keys.add(keys.get(1));
                    keys.remove(1);
                }
                isLeaf = false;
            } else {
                if (!parent.balance(value, this)) {
                    if (parent.keys.size() == degree - 1) {

                    } else {
                        parent.twoToThreeSplit(value);
                    }
                }

            }
        } else {
            searchLeaf(value).add(value);
        }
    }

    private BStarTreeNode<T> searchLeaf(T value) {
        for (int i = 0; i < keys.size(); i++) {
            if (value.compareTo(keys.get(i)) <= 0) {
                if (children.get(i).isLeaf) {
                    return children.get(i);
                } else {
                    return children.get(i).searchLeaf(value);
                }
            }
        }
        if (children.get(keys.size()).isLeaf) {
            return children.get(keys.size());
        } else {
            return children.get(keys.size()).searchLeaf(value);
        }
    }

    private boolean balance(T value, BStarTreeNode<T> leaf) {
        int leafIndex = children.indexOf(leaf);
        for (int i = leafIndex - 1; i >= 0; i--) {
            if (children.get(i).keys.size() < degree - 1) {
                for (int j = i; j < leafIndex; j++) {
                    children.get(j).keys.add(keys.remove(i));
                    keys.add(children.get(j + 1).keys.remove(0));
                    children.get(j).keys.sort(Comparator.naturalOrder());
                }
                keys.sort(Comparator.naturalOrder());
                children.get(leafIndex).keys.add(value);
                children.get(leafIndex).keys.sort(Comparator.naturalOrder());
                return true;
            }
        }
        for (int i = leafIndex + 1; i < children.size(); i++) {
            if (children.get(i).keys.size() < degree - 1) {
                for (int j = i; j > leafIndex; j--) {
                    children.get(j).keys.add(keys.remove(--i));
                    keys.add(children.get(j - 1).keys.remove(children.get(j - 1).keys.size() - 1));
                    children.get(j).keys.sort(Comparator.naturalOrder());
                }
                keys.sort(Comparator.naturalOrder());
                children.get(leafIndex).keys.add(value);
                children.get(leafIndex).keys.sort(Comparator.naturalOrder());
                return true;
            }
        }
        return false;
    }

    private void twoToThreeSplit(T value) {
        List<T> values = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            values.addAll(children.get(i).keys);
            children.get(i).keys.clear();
            values.add(keys.get(i));
        }
        values.addAll(children.get(keys.size()).keys);
        children.get(keys.size()).keys.clear();
        keys.clear();
        values.add(value);
        values.sort(Comparator.naturalOrder());
        children.add(new BStarTreeNode<>(degree, this));
        for (int i = 0; i < (2 * degree - 2) / 3; i++) {
            children.get(0).keys.add(values.remove(0));
        }
        keys.add(values.remove(0));
        for (int i = 0; i < (2 * degree - 1) / 3; i++) {
            children.get(1).keys.add(values.remove(0));
        }
        keys.add(values.remove(0));
        for (int i = 0; i < (2 * degree) / 3; i++) {
            children.get(2).keys.add(values.remove(0));
        }
    }

    public List<T> treeToString() {
        List<T> list = new ArrayList<>();
        if (children.isEmpty()) {
            list.addAll(keys);
        } else {
            for (int i = 0; i < children.size() - 1; i++) {
                list.addAll(children.get(i).treeToString());
                list.add(keys.get(i));
            }
            list.addAll(children.get(children.size() - 1).treeToString());
        }
        return list;
    }
}
