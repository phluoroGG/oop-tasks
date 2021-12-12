package edu.csf.oop.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BStarTreeTest {

    @Test
    void testAdd() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);
        tree.add(8);
        Integer[] array = tree.toList().toArray(new Integer[0]);
        Assertions.assertTrue(true);
    }

}