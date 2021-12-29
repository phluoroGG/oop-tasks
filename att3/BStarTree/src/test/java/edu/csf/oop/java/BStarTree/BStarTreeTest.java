package edu.csf.oop.java.BStarTree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BStarTreeTest {

    @Test
    void testAddDegree3Nodes8() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 9; i++) {
            tree.add(i);
        }
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(6, tree.getRoot().getKeys().get(1));
        Assertions.assertEquals(1, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(2, tree.getRoot().getChildren().get(0).getKeys().get(1));
        Assertions.assertEquals(4, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(5, tree.getRoot().getChildren().get(1).getKeys().get(1));
        Assertions.assertEquals(7, tree.getRoot().getChildren().get(2).getKeys().get(0));
        Assertions.assertEquals(8, tree.getRoot().getChildren().get(2).getKeys().get(1));
    }

    @Test
    void testAddDegree4Nodes15() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 16; i++) {
            tree.add(i);
        }
        Assertions.assertEquals(4, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(8, tree.getRoot().getKeys().get(1));
        Assertions.assertEquals(12, tree.getRoot().getKeys().get(2));
        Assertions.assertEquals(1, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(2, tree.getRoot().getChildren().get(0).getKeys().get(1));
        Assertions.assertEquals(3, tree.getRoot().getChildren().get(0).getKeys().get(2));
        Assertions.assertEquals(5, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(6, tree.getRoot().getChildren().get(1).getKeys().get(1));
        Assertions.assertEquals(7, tree.getRoot().getChildren().get(1).getKeys().get(2));
        Assertions.assertEquals(9, tree.getRoot().getChildren().get(2).getKeys().get(0));
        Assertions.assertEquals(10, tree.getRoot().getChildren().get(2).getKeys().get(1));
        Assertions.assertEquals(11, tree.getRoot().getChildren().get(2).getKeys().get(2));
        Assertions.assertEquals(13, tree.getRoot().getChildren().get(3).getKeys().get(0));
        Assertions.assertEquals(14, tree.getRoot().getChildren().get(3).getKeys().get(1));
        Assertions.assertEquals(15, tree.getRoot().getChildren().get(3).getKeys().get(2));
    }

    @Test
    void testRemoveDegree3Nodes3Number1() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 4; i++) {
            tree.add(i);
        }
        tree.remove(1);
        Assertions.assertEquals(2, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(1));
    }

    @Test
    void testRemoveDegree3Nodes3Number2() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 4; i++) {
            tree.add(i);
        }
        tree.remove(2);
        Assertions.assertEquals(1, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(1));
    }

    @Test
    void testRemoveDegree3Nodes4Number1() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 5; i++) {
            tree.add(i);
        }
        tree.remove(1);
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(2, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(4, tree.getRoot().getChildren().get(1).getKeys().get(0));
    }

    @Test
    void testRemoveDegree3Nodes4Number2() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 5; i++) {
            tree.add(i);
        }
        tree.remove(2);
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(1, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(4, tree.getRoot().getChildren().get(1).getKeys().get(0));
    }

    @Test
    void testRemoveDegree3Nodes6Number1() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 7; i++) {
            tree.add(i);
        }
        tree.remove(1);
        Assertions.assertEquals(4, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(2, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(3, tree.getRoot().getChildren().get(0).getKeys().get(1));
        Assertions.assertEquals(5, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(6, tree.getRoot().getChildren().get(1).getKeys().get(1));
    }

    @Test
    void testRemoveDegree3Nodes6Number2() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 7; i++) {
            tree.add(i);
        }
        tree.remove(2);
        Assertions.assertEquals(4, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(1, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(3, tree.getRoot().getChildren().get(0).getKeys().get(1));
        Assertions.assertEquals(5, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(6, tree.getRoot().getChildren().get(1).getKeys().get(1));
    }

    @Test
    void testRemoveDegree3Nodes6Number6() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 7; i++) {
            tree.add(i);
        }
        tree.remove(6);
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(1, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(2, tree.getRoot().getChildren().get(0).getKeys().get(1));
        Assertions.assertEquals(4, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(5, tree.getRoot().getChildren().get(1).getKeys().get(1));
    }

    @Test
    void testRemoveDegree3Nodes7Number1() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 8; i++) {
            tree.add(i);
        }
        tree.remove(1);
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(5, tree.getRoot().getKeys().get(1));
        Assertions.assertEquals(2, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(4, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(6, tree.getRoot().getChildren().get(2).getKeys().get(0));
        Assertions.assertEquals(7, tree.getRoot().getChildren().get(2).getKeys().get(1));
    }

    @Test
    void testRemoveDegree4Nodes4Number1() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 5; i++) {
            tree.add(i);
        }
        tree.remove(1);
        Assertions.assertEquals(2, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(1));
        Assertions.assertEquals(4, tree.getRoot().getKeys().get(2));
    }

    @Test
    void testRemoveDegree4Nodes4Number2() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 5; i++) {
            tree.add(i);
        }
        tree.remove(2);
        Assertions.assertEquals(1, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(1));
        Assertions.assertEquals(4, tree.getRoot().getKeys().get(2));
    }

    @Test
    void testRemoveDegree4Nodes4Number3() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 5; i++) {
            tree.add(i);
        }
        tree.remove(3);
        Assertions.assertEquals(1, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(2, tree.getRoot().getKeys().get(1));
        Assertions.assertEquals(4, tree.getRoot().getKeys().get(2));
    }

    @Test
    void testRemoveDegree4Nodes5Number1() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 6; i++) {
            tree.add(i);
        }
        tree.remove(1);
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(2, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(4, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(5, tree.getRoot().getChildren().get(1).getKeys().get(1));
    }

    @Test
    void testRemoveDegree4Nodes5Number2() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 6; i++) {
            tree.add(i);
        }
        tree.remove(2);
        Assertions.assertEquals(3, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(1, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(4, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(5, tree.getRoot().getChildren().get(1).getKeys().get(1));
    }

    @Test
    void testRemoveDegree4Nodes8Number1() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 9; i++) {
            tree.add(i);
        }
        tree.remove(1);
        Assertions.assertEquals(5, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(2, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(3, tree.getRoot().getChildren().get(0).getKeys().get(1));
        Assertions.assertEquals(4, tree.getRoot().getChildren().get(0).getKeys().get(2));
        Assertions.assertEquals(6, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(7, tree.getRoot().getChildren().get(1).getKeys().get(1));
        Assertions.assertEquals(8, tree.getRoot().getChildren().get(1).getKeys().get(2));
    }

    @Test
    void testRemoveDegree4Nodes12Number1And2And3() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 12; i++) {
            tree.add(i);
        }
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);
        Assertions.assertEquals(5, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(8, tree.getRoot().getKeys().get(1));
        Assertions.assertEquals(4, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(6, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(7, tree.getRoot().getChildren().get(1).getKeys().get(1));
        Assertions.assertEquals(9, tree.getRoot().getChildren().get(2).getKeys().get(0));
        Assertions.assertEquals(10, tree.getRoot().getChildren().get(2).getKeys().get(1));
        Assertions.assertEquals(11, tree.getRoot().getChildren().get(2).getKeys().get(2));
    }

    @Test
    void testRemoveDegree4Nodes12Number4() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 12; i++) {
            tree.add(i);
        }
        tree.remove(4);
        Assertions.assertEquals(5, tree.getRoot().getKeys().get(0));
        Assertions.assertEquals(8, tree.getRoot().getKeys().get(1));
        Assertions.assertEquals(1, tree.getRoot().getChildren().get(0).getKeys().get(0));
        Assertions.assertEquals(2, tree.getRoot().getChildren().get(0).getKeys().get(1));
        Assertions.assertEquals(3, tree.getRoot().getChildren().get(0).getKeys().get(2));
        Assertions.assertEquals(6, tree.getRoot().getChildren().get(1).getKeys().get(0));
        Assertions.assertEquals(7, tree.getRoot().getChildren().get(1).getKeys().get(1));
        Assertions.assertEquals(9, tree.getRoot().getChildren().get(2).getKeys().get(0));
        Assertions.assertEquals(10, tree.getRoot().getChildren().get(2).getKeys().get(1));
        Assertions.assertEquals(11, tree.getRoot().getChildren().get(2).getKeys().get(2));
    }

    @Test
    void testSearchDegree3Nodes8() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        for (int i = 1; i < 9; i++) {
            tree.add(i);
        }
        for (int i = 1; i < 9; i++) {
            Assertions.assertTrue(tree.contains(i));
        }
    }

    @Test
    void testSearchDegree4Nodes15() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(4);
        for (int i = 1; i < 16; i++) {
            tree.add(i);
        }
        for (int i = 1; i < 16; i++) {
            Assertions.assertTrue(tree.contains(i));
        }
    }

    @Test
    void testToList() throws Exception {
        BStarTree<Integer> tree = new BStarTree<>(3);
        List<Integer> expectedList = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            tree.add(i);
            expectedList.add(i);
        }
        Assertions.assertEquals(expectedList, tree.toList());
    }
}