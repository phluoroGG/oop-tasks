package edu.csf.oop.java.supermarket;

import java.util.Random;

public class Main {
    private static int[] rndArr(int length) {
        int[] arr = new int[length];
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] =  rnd.nextInt(length);
            while (true) {
                boolean u = false;
                for (int j = 0; j < i; j++) {
                    if (arr[j] == arr[i]) {
                        u = true;
                        break;
                    }
                }
                if (u) {
                    arr[i] = rnd.nextInt(length);
                } else {
                    break;
                }
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        ListOfGoods list = new ListOfGoods();
        list.add(new Product(Goods.SOME1, 10, 5));
        list.add(new Product(Goods.SOME2, 10, 5));
        list.add(new Product(Goods.SOME3, 10, 5));
        list.add(new Product(Goods.SOME4, 10, 5));
        int income = 0;
        for (int i = 0; i < 3; i++) {
            income += list.sell(new Customer());
        }
        System.out.println(income);
        list.getStat();
    }
}
