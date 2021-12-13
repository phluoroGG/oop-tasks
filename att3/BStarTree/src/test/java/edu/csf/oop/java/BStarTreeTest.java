package edu.csf.oop.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BStarTreeTest {

    @Test
    void testAdd() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 16; i++) {
            tree.add(i);
        }
        for (int i = 1; i < 16; i++) {
            boolean search = tree.search(i);
            int q = 0;
        }
        boolean search = tree.search(0);
        search = tree.search(17);
        Integer[] array = tree.toList().toArray(new Integer[0]);
        Assertions.assertTrue(true);
    }

}