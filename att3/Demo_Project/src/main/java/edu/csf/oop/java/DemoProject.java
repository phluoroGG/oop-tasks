package edu.csf.oop.java;

import edu.csf.oop.java.BStarTree.BStarTree;
import edu.csf.oop.java.BStarTree.BStarTreeNode;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DemoProject {

    public void drawTree(BStarTreeNode<Integer> parent, BStarTreeNode<Integer> root, String prefix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        List<Integer> keys = root.getKeysReadOnly();
        for (Integer value : keys) {
            stringBuilder.append(value);
            if (keys.indexOf(value) != keys.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        System.out.println(stringBuilder);
        if (prefix.isEmpty()) {
            prefix = "+-" + prefix;
        } else {
            if (root != parent.getChildrenReadOnly().get(parent.getChildrenReadOnly().size() - 1)) {
                prefix = prefix.substring(0, prefix.indexOf('+')) + "| " + prefix.substring(prefix.indexOf('+'));
            } else {
                prefix = prefix.substring(0, prefix.indexOf('+')) + "  " + prefix.substring(prefix.indexOf('+'));
            }
        }
        if (root.getChildrenReadOnly() != null) {
            for (int i = 0; i < root.getChildrenReadOnly().size(); i++) {
                drawTree(root, root.getChildrenReadOnly().get(i), prefix);
            }
        }
    }

    public void drawTree(BStarTree<Integer> tree) {
        String prefix = "";
        drawTree(null, tree.getRoot(), prefix);
    }

    public void start() throws Exception {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);
        int i;
        System.out.println("B*-Tree.");
        System.out.println("Введите степень дерева (>=3):");
        System.out.print(">");
        i = scanner.nextInt();
        BStarTree<Integer> tree = new BStarTree<>(i);
        while (true) {
            System.out.println("1. Добавить элемент в дерево.");
            System.out.println("2. Удалить элемент из дерева.");
            System.out.println("3. Вывести дерево на экран.");
            System.out.println("4. Выйти.");
            System.out.print(">");
            i = scanner.nextInt();
            switch (i) {
                case 1:
                    System.out.println("Введите элемент:");
                    System.out.print(">");
                    i = scanner.nextInt();
                    tree.add(i);
                    break;
                case 2:
                    System.out.println("Введите элемент:");
                    System.out.print(">");
                    i = scanner.nextInt();
                    tree.remove(i);
                    break;
                case 3:
                    drawTree(tree);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Повторите ввод.");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        DemoProject demoProject = new DemoProject();
        demoProject.start();
    }
}
