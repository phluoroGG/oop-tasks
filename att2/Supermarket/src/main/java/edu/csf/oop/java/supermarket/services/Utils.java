package edu.csf.oop.java.supermarket.services;

import java.util.Random;

public class Utils {
    public static int[] rndIntArr(int length) {
        int[] arr = new int[length];
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextInt(length);
            while (true) {
                boolean isHasEqual = false;
                for (int j = 0; j < i; j++) {
                    if (arr[j] == arr[i]) {
                        isHasEqual = true;
                        break;
                    }
                }
                if (isHasEqual) {
                    arr[i] = rnd.nextInt(length);
                } else {
                    break;
                }
            }
        }
        return arr;
    }
}
