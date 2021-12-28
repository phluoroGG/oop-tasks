package edu.csf.oop.java.BStarTree;

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
        root = new BStarTreeNode<>(null);
        this.degree = degree;
    }

    public BStarTreeNode<T> getRoot() {
        return root;
    }

    public int getDegree() {
        return degree;
    }

    public void add(T value) {
        add(searchLeaf(root, value), value);
    }

    private void add(BStarTreeNode<T> node, T value) {
        if (node.getKeys().size() < degree - 1) {
            node.getKeys().add(value);
            node.getKeys().sort(Comparator.naturalOrder());
        } else if (node.getParent() == null) {
            int middle = (int) Math.ceil((double) degree / 2) - 1;
            node.getKeys().add(value);
            node.getKeys().sort(Comparator.naturalOrder());
            BStarTreeNode<T> newRoot = new BStarTreeNode<>(null);
            newRoot.getKeys().add(node.getKeys().remove(middle));
            newRoot.getChildren().add(node);
            newRoot.getChildren().add(new BStarTreeNode<>(newRoot));
            int size = node.getKeys().size();
            for (int i = middle; i < size; i++) {
                newRoot.getChildren().get(1).getKeys().add(node.getKeys().remove(middle));
            }
            node.setParent(newRoot);
            root = newRoot;
        } else {
            if (!balanceAdd(node.getParent(), node, value)) {
                if (node.getParent().getKeys().size() == degree - 1) {
                    /*List<T> values = new ArrayList<>();
                    int position = node.getParent().getChildren().indexOf(node);
                    int middle = (int) Math.ceil((double) degree / 2) - 1;
                    BStarTreeNode<T> parent = node.getParent();
                    if (position < middle) {

                    } else {
                        values.add(value);
                        for (int i = parent.getKeys().size() - 1; i >= middle; i--) {
                            values.add(parent.getKeys().remove(i));
                            BStarTreeNode<T> deletedNode = parent.getChildren().remove(i + 1);
                            values.addAll(deletedNode.getKeys());
                        }
                        values.sort(Comparator.naturalOrder());
                        BStarTreeNode<T> newRoot = new BStarTreeNode<>(null);
                        newRoot.getKeys().add(values.remove(0));
                        newRoot.getChildren().add(root);
                        root.setParent(newRoot);
                        BStarTreeNode<T> newNode = new BStarTreeNode<>(null);
                        root = newNode;
                        for (T valueToAdd : values) {
                            add(valueToAdd);
                        }
                        newRoot.getChildren().add(newNode);
                        newNode.setParent(newRoot);
                        root = newRoot;
                        BStarTreeNode<T> newNode = newRoot.getChildren().get(newRoot.getChildren().size() - 1);
                        newNode.getKeys().add(values.remove(middle));
                        newNode.getChildren().add(new BStarTreeNode<>(newNode));
                        newNode.getChildren().add(new BStarTreeNode<>(newNode));
                        for (int i = 0; i < middle; i++) {
                            newNode.getChildren().get(0).getKeys().add(values.remove(0));
                        }
                        newNode.getChildren().get(1).getKeys().addAll(values);*/
                } else {
                    split(node.getParent(), value);
                }
            }
        }
    }

    private BStarTreeNode<T> searchLeaf(BStarTreeNode<T> node, T value) {
        if (node.isLeaf()) {
            return node;
        }
        for (int i = 0; i < node.getKeys().size(); i++) {
            if (value.compareTo(node.getKeys().get(i)) <= 0) {
                return searchLeaf(node.getChildren().get(i), value);
            }
        }
        return searchLeaf(node.getChildren().get(node.getKeys().size()), value);
    }

    private boolean balanceAdd(BStarTreeNode<T> parent, BStarTreeNode<T> leaf, T value) {
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

    private void split(BStarTreeNode<T> node, T value) {
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
        node.getChildren().add(new BStarTreeNode<>(node));
        for (int i = size; i >= 0; i--) {
            for (int j = 0; j < (size * degree - i) / (size + 1); j++) {
                node.getChildren().get(size - i).getKeys().add(values.remove(0));
            }
            if (!values.isEmpty()) {
                node.getKeys().add(values.remove(0));
            }
        }
    }

    public void remove(T value) {
        BStarTreeNode<T> node = searchNode(root, value);
        if (node != null) {
            remove(node, value);
        }
    }

    private void remove(BStarTreeNode<T> node, T value) {
        if ((node == root && node.countOfKeys() == degree) ||
                (node.getParent() == root && node.getParent().countOfKeys() == degree)) {
            node.getKeys().remove(value);
            int size = root.getChildren().size();
            for (int i = 0; i < size; i++) {
                root.getKeys().addAll(root.getChildren().get(0).getKeys());
                root.getChildren().remove(0);
            }
            root.getKeys().sort(Comparator.naturalOrder());
        } else if (node.isLeaf() && (node.getKeys().size() > 1 || node == root)) {
            node.getKeys().remove(value);
            if (node != root && (node.getParent().countOfKeys() + 1) % degree == 0) {
                unite(node.getParent());
            }
        } else if (!node.isLeaf()) {
            if (node.countOfKeys() % degree == 0) {
                node.getKeys().remove(value);
                unite(node);
                return;
            }
            if (node.getChildren().get(node.getKeys().indexOf(value) + 1).getKeys().size() > 1) {
                node.getKeys().add(node.getChildren().get(node.getKeys().indexOf(value) + 1).getKeys().remove(0));
                node.getKeys().remove(value);
                node.getKeys().sort(Comparator.naturalOrder());
            } else if (node.getChildren().get(node.getKeys().indexOf(value)).getKeys().size() > 1) {
                node.getKeys().add(node.getChildren().get(node.getKeys().indexOf(value)).getKeys().remove(
                        node.getChildren().get(node.getKeys().indexOf(value)).getKeys().size() - 1));
                node.getKeys().remove(value);
                node.getKeys().sort(Comparator.naturalOrder());
            } else {
                int index = node.getKeys().indexOf(value) + 1;
                node.getKeys().add(node.getChildren().get(index).getKeys().remove(0));
                node.getKeys().remove(value);
                node.getKeys().sort(Comparator.naturalOrder());
                balanceRemove(node, node.getChildren().get(index));
            }
        } else if (node.getKeys().size() == 1) {
            node.getKeys().remove(value);
            if (node != root && (node.getParent().countOfKeys() + 1) % degree == 0) {
                unite(node.getParent());
                return;
            }
            balanceRemove(node.getParent(), node);
        }
    }

    private void balanceRemove(BStarTreeNode<T> parent, BStarTreeNode<T> leaf) {
        int leafIndex = parent.getChildren().indexOf(leaf);
        for (int i = leafIndex - 1; i >= 0; i--) {
            if (parent.getChildren().get(i).getKeys().size() > 1) {
                for (int j = leafIndex - 1; j >= i; j--) {
                    parent.getChildren().get(j + 1).getKeys().add(parent.getKeys().remove(j));
                    parent.getKeys().add(parent.getChildren().get(j).getKeys().remove(
                            parent.getChildren().get(j).getKeys().size() - 1));
                }
                parent.getKeys().sort(Comparator.naturalOrder());
                return;
            }
        }
        for (int i = leafIndex + 1; i < parent.getChildren().size(); i++) {
            if (parent.getChildren().get(i).getKeys().size() > 1) {
                for (int j = leafIndex; j < i; j++) {
                    parent.getChildren().get(j).getKeys().add(parent.getKeys().remove(j));
                    parent.getKeys().add(parent.getChildren().get(j + 1).getKeys().remove(0));
                }
                parent.getKeys().sort(Comparator.naturalOrder());
                return;
            }
        }
    }

    private void unite(BStarTreeNode<T> node) {
        List<T> values = new ArrayList<>();
        for (int i = 0; i < node.getChildren().size() - 1; i++) {
            values.addAll(node.getChildren().get(i).getKeys());
            node.getChildren().get(i).getKeys().clear();
        }
        values.addAll(node.getChildren().get(node.getChildren().size() - 1).getKeys());
        node.getChildren().get(node.getKeys().size()).getKeys().clear();
        values.addAll(node.getKeys());
        node.getKeys().clear();
        values.sort(Comparator.naturalOrder());
        node.getChildren().remove(node.getChildren().size() - 1);
        for (int i = 0; i < node.getChildren().size(); i++) {
            for (int j = 0; j < degree - 1; j++) {
                node.getChildren().get(i).getKeys().add(values.remove(0));
            }
            if (!values.isEmpty()) {
                node.getKeys().add(values.remove(0));
            }
        }
    }

    public boolean contains(T value) {
        return searchNode(root, value) != null;
    }

    private BStarTreeNode<T> searchNode(BStarTreeNode<T> node, T value) {
        for (int i = 0; i < node.getKeys().size(); i++) {
            if (value.compareTo(node.getKeys().get(i)) == 0)
                return node;
            if (value.compareTo(node.getKeys().get(i)) < 0) {
                if (node.isLeaf()) {
                    return null;
                }
                return searchNode(node.getChildren().get(i), value);
            }
        }
        if (node.isLeaf()) {
            return null;
        } else {
            return searchNode(node.getChildren().get(node.getKeys().size()), value);
        }
    }


    public List<T> toList() {
        return treeToList(root);
    }

    private List<T> treeToList(BStarTreeNode<T> node) {
        List<T> list = new ArrayList<>();
        if (node.getChildren().isEmpty()) {
            list.addAll(node.getKeys());
        } else {
            for (int i = 0; i < node.getChildren().size() - 1; i++) {
                list.addAll(treeToList(node.getChildren().get(i)));
                list.add(node.getKeys().get(i));
            }
            list.addAll(treeToList(node.getChildren().get(node.getChildren().size() - 1)));
        }
        return list;
    }


}
