package edu.csf.oop.java;

import java.util.ArrayList;
import java.util.Comparator;
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
        if (root.isLeaf()) {
            add(value, root);
        } else {
            add(value, searchLeaf(value, root));
        }
    }

    private void add(T value, BStarTreeNode<T> node) {
        if (node.isLeaf()) {
            if (node.getKeys().size() < degree - 1) {
                node.getKeys().add(value);
                node.getKeys().sort(Comparator.naturalOrder());
            } else if (node.getParent() == null) {
                int middle = (int) Math.ceil((double) degree / 2) - 1;
                node.getKeys().add(value);
                node.getKeys().sort(Comparator.naturalOrder());
                BStarTreeNode<T> newRoot = new BStarTreeNode<>(degree, null);
                newRoot.getKeys().add(node.getKeys().remove(middle));
                newRoot.getChildren().add(node);
                newRoot.getChildren().add(new BStarTreeNode<>(degree, newRoot));
                int size = node.getKeys().size();
                for (int i = middle; i < size; i++) {
                    newRoot.getChildren().get(1).getKeys().add(node.getKeys().remove(middle));
                }
                node.setParent(newRoot);
                newRoot.setLeaf(false);
                root = newRoot;
            } else {
                if (!balance(value, node.getParent(), node)) {
                    if (node.getParent().getKeys().size() == degree - 1) {

                    } else {
                        split(value, node.getParent());
                    }
                }

            }
        }
    }

    private BStarTreeNode<T> searchLeaf(T value, BStarTreeNode<T> node) {
        for (int i = 0; i < node.getKeys().size(); i++) {
            if (value.compareTo(node.getKeys().get(i)) <= 0) {
                if (node.getChildren().get(i).isLeaf()) {
                    return node.getChildren().get(i);
                } else {
                    return searchLeaf(value, node.getChildren().get(i));
                }
            }
        }
        if (node.getChildren().get(node.getKeys().size()).isLeaf()) {
            return node.getChildren().get(node.getKeys().size());
        } else {
            return searchLeaf(value, node.getChildren().get(node.getKeys().size()));
        }
    }

    private boolean balance(T value, BStarTreeNode<T> parent, BStarTreeNode<T> leaf) {
        int leafIndex = parent.getChildren().indexOf(leaf);
        for (int i = leafIndex - 1; i >= 0; i--) {
            if (parent.getChildren().get(i).getKeys().size() < degree - 1) {
                for (int j = i; j < leafIndex; j++) {
                    parent.getChildren().get(j).getKeys().add(parent.getKeys().remove(i));
                    parent.getKeys().add(parent.getChildren().get(j + 1).getKeys().remove(0));
                    parent.getChildren().get(j).getKeys().sort(Comparator.naturalOrder());
                }
                parent.getKeys().sort(Comparator.naturalOrder());
                parent.getChildren().get(leafIndex).getKeys().add(value);
                parent.getChildren().get(leafIndex).getKeys().sort(Comparator.naturalOrder());
                return true;
            }
        }
        for (int i = leafIndex + 1; i < parent.getChildren().size(); i++) {
            if (parent.getChildren().get(i).getKeys().size() < degree - 1) {
                for (int j = i; j > leafIndex; j--) {
                    parent.getChildren().get(j).getKeys().add(parent.getKeys().remove(--i));
                    parent.getKeys().add(parent.getChildren().get(j - 1).getKeys().remove(
                            parent.getChildren().get(j - 1).getKeys().size() - 1));
                    parent.getChildren().get(j).getKeys().sort(Comparator.naturalOrder());
                }
                parent.getKeys().sort(Comparator.naturalOrder());
                parent.getChildren().get(leafIndex).getKeys().add(value);
                parent.getChildren().get(leafIndex).getKeys().sort(Comparator.naturalOrder());
                return true;
            }
        }
        return false;
    }

    private void split(T value, BStarTreeNode<T> node) {
        int size = node.getChildren().size();
        List<T> values = new ArrayList<>();
        for (int i = 0; i < node.getKeys().size(); i++) {
            values.addAll(node.getChildren().get(i).getKeys());
            node.getChildren().get(i).getKeys().clear();
            values.add(node.getKeys().get(i));
        }
        values.addAll(node.getChildren().get(node.getKeys().size()).getKeys());
        node.getChildren().get(node.getKeys().size()).getKeys().clear();
        node.getKeys().clear();
        values.add(value);
        values.sort(Comparator.naturalOrder());
        node.getChildren().add(new BStarTreeNode<>(degree, node));
        for (int i = size; i >= 0; i--) {
            for (int j = 0; j < (size * degree - i) / (size + 1); j++) {
                node.getChildren().get(size - i).getKeys().add(values.remove(0));
            }
            if (!values.isEmpty()) {
                node.getKeys().add(values.remove(0));
            }
        }
    }

    private List<T> treeToString(BStarTreeNode<T> node) {
        List<T> list = new ArrayList<>();
        if (node.getChildren().isEmpty()) {
            list.addAll(node.getKeys());
        } else {
            for (int i = 0; i < node.getChildren().size() - 1; i++) {
                list.addAll(treeToString(node.getChildren().get(i)));
                list.add(node.getKeys().get(i));
            }
            list.addAll(treeToString(node.getChildren().get(node.getChildren().size() - 1)));
        }
        return list;
    }

    public boolean search(T value) {
        return searchValue(value, root);
    }

    private boolean searchValue(T value, BStarTreeNode<T> node) {
        for (int i = 0; i < node.getKeys().size(); i++) {
            if (value.compareTo(node.getKeys().get(i)) == 0)
                return true;
            if (value.compareTo(node.getKeys().get(i)) < 0) {
                if (node.isLeaf()) {
                    return false;
                }
                return searchValue(value, node.getChildren().get(i));
            }
        }
        if (node.isLeaf()) {
            return false;
        } else {
            return searchValue(value, node.getChildren().get(node.getKeys().size()));
        }
    }

    public List<T> toList() {
        return treeToString(root);
    }
}
